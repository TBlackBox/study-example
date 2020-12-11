package algorithm;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtil {
	
	private final static String ENCODE = "GBK";

  
	public static String encrypt(String contant,String password) {
		try {
			return Base64.getEncoder().encodeToString(encrypt(contant.getBytes(ENCODE),password));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加密
	 * @param datasource
	 * @param password
	 * @return
	 */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes(ENCODE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密 正式执行加密操作
            //按单部分操作加密或解密数据，或者结束一个多部分操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
        	System.out.println("加密错误，错误信息：" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src
     * @param password
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes(ENCODE));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//返回实现指定转换的 Cipher 对象
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
    
    public static String decrypt(String contant,String password) {
    	try {
			return new String(decrypt(Base64.getDecoder().decode(contant.getBytes()), password));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    public static void main(String[] args) {
		
    	String contant = "I LOVE YOU";
    	//密码保存8位DES 加解密的算法
    	//加密的key必须为8位或者8的倍数，否则在java加密过程中会报错
    	String password = "12345678";
    	
    	String encryptContant = encrypt(contant, password);
    	System.out.println(encryptContant); //6ANJS1RDMkhlDvhVr3Z5NQ==
    	
    	String decryptContant = decrypt(encryptContant, password);
    	System.out.println(decryptContant); //I LOVE YOU
    	
	}

}