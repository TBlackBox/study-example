package guava.net;

import java.net.InetAddress;

import com.google.common.net.InetAddresses;

public class Ip {

	public static void main(String[] args) {
		InetAddress inetAddress = InetAddresses.forString("198.168.23.123");
		int intIp = InetAddresses.coerceToInteger(inetAddress);
		System.out.println(intIp);
		
		InetAddress inetAddress1 = InetAddresses.forString("0.0.0.0");
		int intIp1 = InetAddresses.coerceToInteger(inetAddress1);
		System.out.println(intIp1);
	}
}
