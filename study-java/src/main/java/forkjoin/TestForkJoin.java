package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestForkJoin {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//需要用到ForkJoinPool线程池类运行线程
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinTest(0L, 10000000000L);
		
		long sum = pool.invoke(task);
		System.out.println("计算结果："+sum);
		
		long end = System.currentTimeMillis();
		System.out.println("耗费的时间为: " + (end - start)); 
	}
}
