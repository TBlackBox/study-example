import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Test {

	
	public static void main(String[] args) {
		long timestamp = System.currentTimeMillis();
		
//		String ip = "127.0.0.1";
		
		String key = "12345678";
		
//		String content = String.format("timestamp=%s&ip=%s", timestamp,ip); 
		
//		String sign = AESUtils.aesCBCEncrypt(content, "UTF-8", key, "");
		byte[] signByte = aes128CBCEncrypt("timestamp=402232232323&ip=172.16.7.33".getBytes(), key, key);
		System.out.println(bytesToHexString(signByte));
		
	}
	
	
	
	public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
 
            stringBuilder.append(i);
 
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
	
	
	private static final String CipherMode = "AES/CBC/PKCS5Padding";
	public static byte[] aes128CBCEncrypt(byte[] content, String password, String iv) {
	        try {
	            Cipher cipher = Cipher.getInstance(CipherMode);
//	        	 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, createKey(password), createIV(iv));
	            byte[] result = cipher.doFinal(content);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	
	 /**
     * 创建密钥
     *
     * @param key
     * @return
     */
    private static SecretKeySpec createKey(String key) {
        key = key == null ? "" : key;
        StringBuilder sb = new StringBuilder(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
 
        byte[] data = null;
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }
    
    /**
     * 创建初始化向量
     *
     * @param password
     * @return
     */
    private static IvParameterSpec createIV(String password) {
        password = password == null ? "" : password;
        StringBuilder sb = new StringBuilder(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
 
        byte[] data = null;
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }
	
}
