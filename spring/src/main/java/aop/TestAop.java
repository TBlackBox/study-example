package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//通过声明的形式引入
public class TestAop {

	public static void main(String[] args) {
		// 获取 Spring Context
		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
		// 从 Context 中根据 id 获取 Bean 对象（其实就是一个代理）
		IUserDao userDao = (IUserDao) context.getBean("userDaoProxy");                       
        // 调用代理的方法
        userDao.save();                                                              
	}
}
