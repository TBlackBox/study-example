package jdk;

import org.junit.Test;

public class MainTest {

	@Test
	public void testInteger() {
		//System.out.println(Integer.max(1, -2));
		
		Integer a = 128;
		Integer b = 128;
		System.out.println(a == b); //false
		System.out.println(a.equals(b)); //true
		
		System.out.println(Integer.toString(-4, 2));
	}
}
