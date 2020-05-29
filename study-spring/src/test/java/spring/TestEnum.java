package spring;

import strategy.PrintCommandEnum;

public class TestEnum {

	public static void main(String[] args) {
		PrintCommandEnum[] values = PrintCommandEnum.values();
		for(PrintCommandEnum printCommandEnum:values) {
			System.out.println(printCommandEnum.name());
			System.out.println(printCommandEnum.ordinal());
			System.out.println(printCommandEnum.values());
			System.out.println(printCommandEnum.getMsg() + "   " + printCommandEnum.getCommand());
		}
	}
}
