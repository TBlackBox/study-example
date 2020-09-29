package string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringJoinTest {

	@Test
	public void testFormat() {
		String value = String.format("%s是一个屌丝！", "张三");
		System.out.println(value);
		
		int a = 12800;
		int b = 12800;
		System.out.println(a == b);
	}
	
	
	@Test
	public void testJoin() {
		String value = String.join(":", "1","2","3");
		//输出：1:2:3
		System.out.println(value);
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		String value2 = String.join(":", list);
		//输出：a:b:c
		System.out.println(value2);
	}
}
