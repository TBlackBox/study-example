package hello;

import java.util.function.Consumer;

public class TestLambad {
    public static void main(String[] args) {
    	//消费类型的
    	new test().consumer(1200,(e) -> System.out.println("吃饭消费："+ e+ "元"));
    	
    	//供给行的
    }
}


class test{
	
	 public void consumer(Integer money,Consumer<Integer> con) {
	    	con.accept(money);
	    }
}