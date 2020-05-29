package strategy;

import org.springframework.stereotype.Component;

@Component
public class BooleanPrintCommand implements PrintCommand{

	@Override
	public void print() {
		System.out.println("打印Boolea:true");
	}

}
