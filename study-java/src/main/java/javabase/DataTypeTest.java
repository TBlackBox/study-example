package javabase;

import gson.SimpleTestMain;
import gson.SimpleTestMain.User;

public class DataTypeTest {
	
	static int b = 545;
	
	//属性块  里面声明的变量就是块级别变量
	static{
		int a =19;
		System.out.println("哈哈  这是熟悉块");
		System.out.println(b);
	}

	public static void main(String[] args) {
		System.out.println("main 方法开始");
		int a = 1_00_000;
		
		long z=0x22L;  // 十六进制
		
		double d = 1000;
		
		
		//输出是10进制
		System.out.println(z);
		
		String str = "aaa";
		
		int arr[][] = {{1,2,3,4,5},{1,2,3,4,5}};
		System.out.println(arr[0][1]);
		
		User user = new SimpleTestMain().new User("dd", 12,true);
		
		new DataTypeTest().test1(12);
		DataTypeTest.test2();
		DataTypeTest.test2(11);
		
	}
	
	private void test() {
		
	}
	
	private void test(int a) {
		
	}
	
	public final void test1() {
		
	}
	
	public final void test1(int a) {
		System.out.println(a);
	}
	
	final static void test2() {
		System.out.println("statc");
	    testClass t = new testClass();
	    System.out.println(t.a);
	}
	
	final static void test2(int a) {
		System.out.println("a=" + a);
	}
}

class testClass{
	public int a = 1122;
}
