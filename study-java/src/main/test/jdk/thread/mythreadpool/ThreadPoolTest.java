package jdk.thread.mythreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	
	public static void main(String[] args) {
		//核心数量为5，最大数量为10，空闲时间为1秒，队列长度为5，拒绝策略打印一句话。
		Executor executor = new ThreadPoolExecutor(5, 
				10, 
				1, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(5),
				new RejectedExecutionHandler() {
					
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("线程：" + Thread.currentThread().getName() + ",进入拒绝策略");
					}
				});
		
		//提交20个任务
		for(int i = 0; i < 20; i++) {
			final int num = i;
			executor.execute(()->{
				System.out.println(Thread.currentThread().getName() + ",第：" + num + "是运行的" + System.currentTimeMillis());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
	}
	
}
