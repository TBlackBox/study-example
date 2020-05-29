package strategy;

import org.springframework.stereotype.Component;

@Component
public class StringPrintCommand implements PrintCommand{

	@Override
	public void print() {
		System.out.println("打印字符串:abcdefg");
	}
}
