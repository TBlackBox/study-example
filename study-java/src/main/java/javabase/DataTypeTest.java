package javabase;

import gson.SimpleTestMain;
import gson.SimpleTestMain.User;

public class DataTypeTest {

	public static void main(String[] args) {
		int a = 1_00_000;
		
		long z=0x22L;  // 十六进制
		
		double d = 1000;
		
		
		//输出是10进制
		System.out.println(z);
		
		String str = "aaa";
		
		int arr[][] = {{1,2,3,4,5},{1,2,3,4,5}};
		System.out.println(arr[0][1]);
		
		User user = new SimpleTestMain().new User("dd", 12,true);
		
	}
}
