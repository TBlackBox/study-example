package ppvod;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 云转码 签名算法
 */
public class SignUtil {
	private String mKey;
	public SignUtil(String key) {
		this.mKey = key;
	}

	class KeyIv {
		byte[] key;
		byte[] iv;
	}
	
	// 加密
	public String encrypt(String sSrc) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		KeyIv keyiv = md5(mKey);
		byte[] raw = keyiv.key;
		
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		// IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0,
		// 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec(keyiv.iv);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

		String s = Utils.toHexString(encrypted);
		return s;
	}

	// 解密
	public String decrypt(String sSrc) throws Exception {
		try {
			KeyIv keyiv = md5(mKey);
			byte[] raw = keyiv.key;
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			
			IvParameterSpec iv = new IvParameterSpec(keyiv.iv);// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Utils.hexStringToByteArray(sSrc);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			System.out.println(ex);
			return null;

		}
	}
	
	public KeyIv md5(String key) throws NoSuchAlgorithmException {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		byte[] keys = key.getBytes(); 
		md.update(keys);
		byte[] ckey = md.digest();
		byte[] s2 = new byte[16 + keys.length];
		System.arraycopy(ckey,0, s2, 0, 16);
		System.arraycopy(keys, 0, s2, 16, keys.length);
		 md = java.security.MessageDigest.getInstance("MD5");
		md.update(s2);
		byte[] civ = md.digest();
		KeyIv kv = new KeyIv();
		kv.key = ckey;
		kv.iv = civ;
		return kv;
	}
	
	//签名
	public String sign(String ip) throws Exception {
		return  encrypt("timestamp=" + System.currentTimeMillis() + "&ip=" + ip);
	}
	//解析
	public String unsign(String content) throws Exception {
		return  decrypt(content);
	}
	public static void main(String[] args) throws Exception {
		String key = "12345678"; //秘钥
		SignUtil util = new SignUtil(key);
		String strSign = util.sign("127.0.0.1");
		System.out.println(strSign);
//		String verify = util.unsign(strSign);
//		System.out.println(verify);
		// 需要加密的字串
		
//		String cSrc = "timestamp=402232232323";
//		System.out.println(cSrc);
		// 加密
//		long lStart = System.currentTimeMillis();
//		String enString = util.encrypt(cSrc);
//		System.out.println("加密后的字串是：" + enString);
		/**
		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = util.decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
		**/
	}
}