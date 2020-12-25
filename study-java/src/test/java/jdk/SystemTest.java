package jdk;

import org.junit.Test;

public class SystemTest {

	@Test
	public void testSystem() {
		
		int[] src = {1,2,3,4,5};
		
		int[] dest = {10,11,12,13,14};
		
		System.arraycopy(src, 1, dest, 2, 2);
		
		//dest [10,11,2,3,14]
		
		for(int i : dest) {
			System.out.println(i);
		}
		
		long timeMill = System.currentTimeMillis();
		System.out.println("当前系统毫秒时间：" + timeMill); //当前系统毫秒时间：1608523828707
		long timeNano = System.nanoTime();
		System.out.println("当前系统纳秒时间：" + timeNano); //当前系统纳秒时间：2945068891755425
		
	}
	
	@Test
	public void listProperties() {
//		System.getProperties().list(System.out) ;	// 列出系统的全部属性
		
		//设置系统的
		System.setProperty("sun.desktop", "Linux");
		
		String desktop = System.getProperty("sun.desktop");
		System.out.println(desktop);//Linux
		//自定义
		System.setProperty("test", "哈哈");
		System.out.println(System.getProperty("test")); //哈哈
		
	}
	
}
