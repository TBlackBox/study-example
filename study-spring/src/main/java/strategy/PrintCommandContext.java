package strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import spring.helper.SpringHelper;

public class PrintCommandContext {

	public static PrintCommand getInstance(String printCommand){
		String commandClazz = PrintCommandEnum.getCommand(printCommand);
		if(StringUtils.isEmpty(commandClazz)) {
			commandClazz = PrintAllCommend.class.getName();
		} 
		
		ApplicationContext applicationContext = SpringHelper.applicationContext;
		
		String[]  names = applicationContext.getBeanDefinitionNames();
		
		//applicationContext.getBean(Class.forName(commandClazz));//通过类获取
		return (PrintCommand) applicationContext.getBean(commandClazz);
	}
}
