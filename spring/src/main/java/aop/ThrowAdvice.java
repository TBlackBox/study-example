package aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
public class ThrowAdvice implements ThrowsAdvice{

	 public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
	        System.out.println("---------- 获取到的异常 ----------");
	        System.out.println("目标类: " + target.getClass().getName());
	        System.out.println("方法名字: " + method.getName());
	        System.out.println("异常信息: " + e.getMessage());
	        System.out.println("-------------------------------------");
	    }
}
