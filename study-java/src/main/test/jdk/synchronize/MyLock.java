package jdk.synchronize;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import sun.misc.Unsafe;

public class MyLock {

	//变量  为1时表示加锁  为0时 表示解锁
	
	private volatile int state = 0;
	
	private static long tailOffset;
	
	private static long offset;
	
    private static Unsafe unsafe;
    
    
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            Class clazz = MyLock.class;
            
//            Field tailOffsetField = clazz.getDeclaredField("tailOffset");
//            tailOffsetField.setAccessible(true);
//            tailOffset = unsafe.objectFieldOffset(tailOffsetField);
//            System.out.println(tailOffset);
            
            Field stateField = clazz.getDeclaredField("state");
            offset = unsafe.objectFieldOffset(stateField);
            
           
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, offset, expect, update);
    }
	
    
    private static class Node {
        // 存储的元素为线程
        Thread thread;
        // 前一个节点（可以没有，但实现起来很困难）
        Node prev;
        // 后一个节点
        Node next;

        public Node() {
        }

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }
    }
    // 链表头
    private volatile Node head;
    // 链表尾
    private volatile Node tail;
    // 原子更新tail字段
    private boolean compareAndSetTail(Node expect, Node update) {
    	synchronized (this) {
    		expect = update;
    		return true;
		}
    	//TODO: 没搞好
//        return unsafe.compareAndSwapObject(this, offset, expect, update);
    }
    
    // 入队
    private Node enqueue() {
        while (true) {
            // 获取尾节点
            Node t = tail;
            // 构造新节点
            Node node = new Node(Thread.currentThread(), t);
            // 不断尝试原子更新尾节点
            if (compareAndSetTail(t, node)) {
                // 更新尾节点成功了，让原尾节点的next指针指向当前节点
            	if(t != null) {
            		t.next = node;
            	}
                
                return node;
            }
        }
    }
    
    
	
	//加锁
	public void lock() {
		// 尝试更新state字段，更新成功说明占有了锁
	    if (compareAndSetState(0, 1)) {
	        return;
	    }
	    
	    
	    //在这里  没有获取到锁  就把该线程放到队列里面
	    // 未更新成功则入队
	    Node node = enqueue();
	    Node prev = node.prev;
	    // 再次尝试获取锁，需要检测上一个节点是不是head，按入队顺序加锁
	    while (node.prev != head || !compareAndSetState(0, 1)) {
	        // 未获取到锁，阻塞
	        unsafe.park(false, 0L);
	    }
	    // 下面不需要原子更新，因为同时只有一个线程访问到这里
	    // 获取到锁了且上一个节点是head
	    // head后移一位
	    head = node;
	    // 清空当前节点的内容，协助GC
	    node.thread = null;
	    // 将上一个节点从链表中剔除，协助GC
	    node.prev = null;
	    if(prev != null) {
	    	 prev.next = null;
	    }
	   
	}
	// 解锁
	public void unlock() {
	    // 把state更新成0，这里不需要原子更新，因为同时只有一个线程访问到这里
	    state = 0;
	    // 下一个待唤醒的节点
	    Node next = head.next;
	    // 下一个节点不为空，就唤醒它
	    if (next != null) {
	        unsafe.unpark(next.thread);
	    }
	}
	
	
	//测试
	private static int count = 0;

	public static void main(String[] args) throws InterruptedException {
	    MyLock lock = new MyLock();

	    CountDownLatch countDownLatch = new CountDownLatch(1000);

	    IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
	        lock.lock();

	        try {
	            IntStream.range(0, 10000).forEach(j -> {
	            	System.out.println("ss");
	                count++;
	            });
	        } finally {
	            lock.unlock();
	        }
	            System.out.println(Thread.currentThread().getName());
	        countDownLatch.countDown();
	    }, "tt-" + i).start());

	    countDownLatch.await();

	    System.out.println(count);
	}
	
}
