package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//通过声明的形式引入
public class TestAop {

//	public static void main(String[] args) {
//		// 获取 Spring Context
//		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
//		// 从 Context 中根据 id 获取 Bean 对象（其实就是一个代理）
//		UserDaoImpl userDaoImpl = (userDaoImpl) context.getBean("userDaoProxy");                       
//        // 调用代理的方法
//		userDaoImpl.save();    
//        
//        
//	}
	
	public static void main(String[] args) {
		// 获取 Spring Context
		ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
		// 注意：转型为目标类，而并非它的 Greeting 接口
		UserDaoImpl userDaoImpl = (UserDaoImpl) context.getBean("userDaoProxy");                       
        // 调用代理的方法
		userDaoImpl.save(); 
		
		// 将目标类强制向上转型为 IAdminDao 接口（这是引入增强给我们带来的特性，也就是“接口动态实现”功能）
		IAdminDao adminDao = (IAdminDao) userDaoImpl;
		adminDao.delete();
        
	}
}
