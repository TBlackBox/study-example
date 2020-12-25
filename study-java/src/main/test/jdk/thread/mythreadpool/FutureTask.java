package jdk.thread.mythreadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class FutureTask<T> implements Runnable, Future<T> {

    /**
     * 真正的任务
     */
    private Callable<T> task;
    /**
     * 任务执行的结果
     * 如果执行正常，返回结果为T
     * 如果执行异常，返回结果为Exception
     */
    private Object result;
    /**
     * 任务执行的状态，0未开始，1正常完成，2异常完成
     * 也可以使用volatile+Unsafe实现CAS操作
     */
    private AtomicInteger state = new AtomicInteger(NEW);
    private static final int NEW = 0;
    private static final int FINISHED = 1;
    private static final int EXCEPTION = 2;
    /**
     * 调用者线程
     * 也可以使用volatile+Unsafe实现CAS操作
     */
    private AtomicReference<Thread> caller = new AtomicReference<>();

    public FutureTask(Callable<T> task) {
        this.task = task;
    }

    @Override
    public void run() {
        // 如果状态不是NEW，说明执行过了，直接返回
        if (state.get() != NEW) {
            return;
        }
        try {
            // 执行任务
            T r = task.call();
            // CAS更新state的值为FINISHED
            // 如果更新成功，就把r赋值给result
            // 如果更新失败，说明state的值不为NEW了，也就是任务已经执行过了
            if (state.compareAndSet(NEW, FINISHED)) {
                this.result = r;
                // finish()必须放在修改state里面，见下面的分析
                finish();
            }
        } catch (Exception e) {
            // 如果CAS更新state的值为EXCEPTION成功，就把e赋值给result
            // 如果CAS更新失败，说明state的值不为NEW了，也就是任务已经执行过了
            if (state.compareAndSet(NEW, EXCEPTION)) {
                this.result = e;
                // finish()必须放在修改state里面，见下面的分析
                finish();
            }
        }
    }

    private void finish() {
        // 检查调用者是否为空，如果不为空，唤醒它
        // 调用者在调用get()方法的进入阻塞状态
        for (Thread c; (c = caller.get()) != null;) {
            if (caller.compareAndSet(c, null)) {
                LockSupport.unpark(c);
            }
        }
    }

    public T gets() {
        int s = state.get();
        // 如果任务还未执行完成，判断当前线程是否要进入阻塞状态
        if (s == NEW) {
            for (;;) {
                s = state.get();
                if (s > NEW) {
                    break;
                }
                // 这种方式不能保证线程安全
                // 试想在下面打个断点，state==NEW的时候到这里断下来
                // 然后上面run()方法运行的时候执行到finish()方法时并不会执行unpark()方法
                // 待run()执行完毕，再释放此处的断点，当前线程就park住了
                if (caller.compareAndSet(null, Thread.currentThread())) {
                    LockSupport.park();
                }
            }
        }

        if (s == FINISHED) {
            return (T) result;
        }
        throw new RuntimeException((Throwable) result);
    }

    @Override
    public T get() {
        int s = state.get();
        // 如果任务还未执行完成，判断当前线程是否要进入阻塞状态
        if (s == NEW) {
            // 标识调用者线程是否被标记过
            boolean marked = false;
            for (;;) {
                // 重新获取state的值
                s = state.get();
                // 如果state大于NEW说明完成了，跳出循环
                if (s > NEW) {
                    break;
                    // 此处必须把caller的CAS更新和park()方法分成两步处理，不能把park()放在CAS里面
                } else if (!marked) {
                    // 尝试更新调用者线程
                    // 试想断点停在此处
                    // 此时state为NEW，让run()方法执行到底，它不会执行finish()中的unpark()方法
                    // 这时打开断点，这里会更新caller成功，但是循环从头再执行一遍发现state已经变了，
                    // 直接在上面的if(s>NEW)处跳出循环了，因为finish()在修改state内部
                    marked = caller.compareAndSet(null, Thread.currentThread());
                } else {
                    // 调用者线程更新之后park当前线程
                    // 试想断点停在此处
                    // 此时state为NEW，让run()方法执行到底，因为上面的caller已经设置值了，
                    // 所以会执行finish()方法中的unpark()方法，
                    // 这时再打开断点，这里不会park信
                    // 见unpark()方法的注释，上面写得很清楚：
                    // 如果线程执行了park()方法，那么执行unpark()方法会唤醒那个线程
                    // 如果先执行了unpark()方法，那么线程下一次执行park()方法将不会阻塞
                    LockSupport.park();
                }
            }
        }

        if (s == FINISHED) {
            return (T) result;
        }
        throw new RuntimeException((Throwable) result);
    }
}
