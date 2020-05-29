package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import strategy.PrintCommand;
import strategy.PrintCommandContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTest {

	@Test
	public void testDtrategy(){
		PrintCommand paPrintCommand = PrintCommandContext.getInstance("STING_VLAUE");
		paPrintCommand.print();  
	}
}
