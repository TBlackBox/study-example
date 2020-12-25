package jdk.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

public class ArrayBlockingQueueTest {

	@Test
	public void test() {
		ArrayBlockingQueue a = new ArrayBlockingQueue<String>(5);
		
		int num = Runtime.getRuntime().availableProcessors();
		System.out.println(num);
		
		SynchronousQueue s = new SynchronousQueue();
	}
}
