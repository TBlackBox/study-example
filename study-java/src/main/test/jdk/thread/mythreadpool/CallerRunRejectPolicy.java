package jdk.thread.mythreadpool;

/**
 * 调用者自己执行
 */
public class CallerRunRejectPolicy implements RejectPolicy {
    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        // 当前线程自己执行
        System.out.println("caller run task");
        task.run();
    }
}