package test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void test() {
		Pattern pattern = Pattern.compile("\\w+");
		Pattern pattern1 = Pattern.compile("\\w+", Pattern.CASE_INSENSITIVE);
		System.out.println(pattern.pattern());  //\w+
		System.out.println(pattern.flags()); //0
		
		//返回true 
		Pattern.matches("\\d+","1241");
		//返回false,需要匹配到所有字符串才能返回true,这里ss不能匹配到 
		Pattern.matches("\\d+","1212ss");
		
		String a = "abcd4dds5fa";
		String[] strArr =  a.split("\\d"); //["abcd","dds",fa]
		
		Pattern pattern2 = Pattern.compile("\\d");
		//limit 结果阀值
		strArr = pattern2.split(a); //["abcd","dds",fa]
		
		for(String str:strArr) {
			System.out.println(str);
		}
		
		Pattern p =Pattern.compile("\\d+"); 
		Matcher m = p.matcher("251ddd21f"); 
		//返回p 也就是返回该Matcher对象是由哪个Pattern对象的创建的 
		m.pattern();
		
	}
}
