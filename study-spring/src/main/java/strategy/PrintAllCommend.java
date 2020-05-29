package strategy;

import org.springframework.stereotype.Component;

@Component
public class PrintAllCommend implements PrintCommand {

	@Override
	public void print() {
		System.out.println("打印所有类型");
	}

}
