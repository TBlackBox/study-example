package jdk8time;

import java.time.Instant;

public class mainTest {

	public static void main(String[] args) {
		//1970-01-01T00:00:00 + 120毫秒的时间
		Instant instant1 = Instant.ofEpochMilli(120);
		//1970-01-01T00:00:00 + 120秒的时间
		Instant instant2 = Instant.ofEpochSecond(120);
		//1970-01-01T00:00:00 + 120秒 + 1000000纳秒的时间
		Instant instant3 = Instant.ofEpochSecond(120, 1000000);
		System.out.println(instant1);
		System.out.println(instant2);
		System.out.println(instant3);
	}
}
