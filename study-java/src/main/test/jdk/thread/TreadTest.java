package jdk.thread;

public class TreadTest {

	public static void main(String[] args) {
		SecurityManager s = System.getSecurityManager();
		ThreadGroup group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		
		
		Runnable target = () ->{
			System.out.println("嘿嘿");
		};
		
		Thread thread= new Thread(group, target, "test", 0);
		
		thread.isDaemon();
		
		thread.start();
	}
}
