package test.regex;

import java.util.regex.Pattern;

public class RegexUtils {

	//验证手机号码
	public static boolean verifyPhone(String phone) {
		if(Pattern.matches("^1[3456789]\\d{9}$", phone)) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyAccount(String account) {
		if(Pattern.matches("^\\w{6,20}", account)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		//System.out.println(verifyPhone("13583262514"));
		System.out.println(verifyAccount("12seex"));
	}
}
