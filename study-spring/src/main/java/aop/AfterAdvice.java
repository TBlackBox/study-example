package aop;

import java.lang.reflect.Method;
import org.springframework.aop.AfterReturningAdvice;

//后置增强类
public class AfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("关闭事务");
	}
}
