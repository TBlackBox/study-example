package jdk;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.Test;

public class AtomicIntegerTest {

	private static int count = 0;
	
	private static void increment() {
		count++;
	}
	
	public static void main(String[] args) {
		
		IntStream.range(0, 100).forEach((i) -> {
			new Thread(() -> {
				IntStream.range(0, 1000).forEach((j) -> {
					AtomicIntegerTest.increment();
				});
			},"线程-"+i).start();
		});
		
		 // 这里使用2或者1看自己的机器
        // 我这里是用run跑大于2才会退出循环
        // 但是用debug跑大于1就会退出循环了
        while (Thread.activeCount() > 1) {
            // 让出CPU
            Thread.yield();
        }

        System.out.println(count);
        
        //通过dubug 出来的数据有问题
        //解决方法
        //1. 通过加锁 synchronized
        //2. 通过原子类 AtomicInteger 把 int改为 AtomicInteger
	}
	
	@Test
	public void testAtomicInteger() {
		AtomicInteger count = new AtomicInteger(5);
		System.out.println(count.getAndIncrement());
		System.out.println(count.intValue());
		System.out.println(count.incrementAndGet());
		System.out.println(count.intValue());
		
		System.out.println(count.addAndGet(2));
		int q = 0;
	}
}
