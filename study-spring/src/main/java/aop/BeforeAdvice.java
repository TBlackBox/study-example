package aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
//前置增强类
public class BeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("开启事务");
	}

}
