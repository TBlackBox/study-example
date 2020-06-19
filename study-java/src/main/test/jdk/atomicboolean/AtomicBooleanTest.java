package jdk.atomicboolean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest implements Runnable{

	private static AtomicBoolean exit = new AtomicBoolean(false);
	
	@Override
	public void run() {
			
		if(exit.compareAndSet(false, true)) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + "进入 if1;");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + "进入 if2;");
			
			exit.set(false);
		}else {
			System.out.println(Thread.currentThread().getName() + "进入 else;");
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService ExecutorService = Executors.newFixedThreadPool(2);
		
		ExecutorService.submit(new AtomicBooleanTest());
		ExecutorService.submit(new AtomicBooleanTest());
		ExecutorService.shutdown();
	}
}
