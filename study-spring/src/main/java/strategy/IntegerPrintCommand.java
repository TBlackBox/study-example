package strategy;

import org.springframework.stereotype.Component;

@Component
public class IntegerPrintCommand implements PrintCommand {

	@Override
	public void print() {
		System.out.println("打印数字:2");
	}

}
