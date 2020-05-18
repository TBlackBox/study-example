package algorithm;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {

	/**
	 * 解密
	 */
	public static byte[] decryptBASE64(String key) throws Exception {  
	    return (new BASE64Decoder()).decodeBuffer(key);  
	}  
	
	
	/**
	*加密
	 */
	public static String encryptBASE64(byte[] key) throws Exception {  
	    return (new BASE64Encoder()).encodeBuffer(key);  
	}  
	
	public static void main(String[] args) throws Exception {
		String as = "ddd";
//		String str = Base64Utils.encryptBASE64(Byte);
//		System.out.println("加密的字节数组为："+ str);
//		System.out.println("解密数据为："+Base64Utils.decryptBASE64(b));
	}
}
