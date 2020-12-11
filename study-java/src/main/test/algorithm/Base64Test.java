package algorithm;

import java.util.Base64;

import org.junit.Test;

/**
 * base64 测试
 * @author Administrator
 *
 */
public class Base64Test {

	@Test
	public void test() {
		
		String content = "YOU";
		
		String encoderCntent = Base64.getEncoder().encodeToString(content.getBytes());
		System.out.println(encoderCntent); //WU9V
		
		byte[] b = Base64.getDecoder().decode(encoderCntent);
		
		System.out.println(new String(b)); //you
		
	}
}
