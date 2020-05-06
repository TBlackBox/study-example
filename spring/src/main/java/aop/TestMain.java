package aop;

import org.springframework.aop.framework.ProxyFactory;
/**
 * 通过编码的形式应为
 * @author Administrator
 *
 */
public class TestMain {

	public static void main(String[] args) {
		// 创建代理工厂
		ProxyFactory proxyFactory = new ProxyFactory();     
		// 射入目标类对象 如果是接口的话  那这里介绍接口对应的实现
        proxyFactory.setTarget(new UserDao()); 
        
        /*
                       * 添加各种增强
         */
        // 添加前置增强
        //proxyFactory.addAdvice(new BeforeAdvice());
        // 添加后置增强 
        //proxyFactory.addAdvice(new AfterAdvice()); 
        //proxyFactory.addAdvice(new BeforeAndAfterAdvice());
        proxyFactory.addAdvice(new AroundAdvice());

        // 从代理工厂中获取代理
        UserDao userDao = (UserDao) proxyFactory.getProxy();
        // 调用代理的方法
        userDao.save();                              
	}

}
