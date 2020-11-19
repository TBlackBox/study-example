package base;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		Cat cat= new Cat();
		
		Dog dog = new Dog();
		cat.name();
		dog.name();
		
		Animal animal = cat;
		animal.name();
		
		cat.defalutMonth();
//		test(dog);
		double a = 3.41212132332242;
		
		short s1 = 1;
		s1 = (short) (s1 +1);
		s1 += 1;
		
		String b = "dd32423s";
		System.out.println(b.hashCode());
		
		new Thread(() ->{
			try {
				TimeUnit.MINUTES.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
		Thread t = new Thread(() ->{
			try {
				while (true) {
					System.out.println("开始线程休眠");
					TimeUnit.MINUTES.sleep(3);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t.setDaemon(true);
		System.out.println(t.isInterrupted());;
		
		t.start();
		
		System.out.println("主线程执行完毕");
	}

	private static void test(Dog dog) {
		dog.color = "白色";
	}
}
