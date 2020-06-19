package jdk.atomicboolean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NormalBoolean implements Runnable{

	private static boolean exit = false;
	
	@Override
	public void run() {
			
		if(!exit) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + "进入 if1;");
			
			exit = true;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + "进入 if2;");
			
			exit = false;
			
			System.out.println(Thread.currentThread().getName() + "进入 if3;");
		}else {
			System.out.println(Thread.currentThread().getName() + "进入 else;");
		}
	}
	
	public static void main(String[] args) {
//		Thread.currentThread().setName("主线程");
//		new NormalBoolean().run();
		
		ExecutorService ExecutorService = Executors.newFixedThreadPool(2);
		
		ExecutorService.submit(new NormalBoolean());
		ExecutorService.submit(new NormalBoolean());
		
		ExecutorService.shutdown();
	}
}
