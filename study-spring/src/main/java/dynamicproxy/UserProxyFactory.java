package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import staticproxy.IUserService;

public class UserProxyFactory {

	private IUserService target;
	
	public UserProxyFactory(IUserService target) {
		this.target = target;
	}
	
	
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
		            @Override
		            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		              //这里可以做一些事情，例如跳舞的准备工作
		        		System.out.println("我正在化妆");
		        		//调用方法
		        		method.invoke(target, args);
		        		//这里可以做一些事情 ，例如跳完的事后工作
		        		System.out.println("我正在卸妆");
		                return null;
		            }
        });
	}
	
	//通个箭头函数改写InvocationHandler的实现
	public Object getProxyInstance2() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
               (proxy,method,args) ->{
	            	 //这里可以做一些事情，例如跳舞的准备工作
	           		System.out.println("我正在化妆");
	           		//调用方法
	           		method.invoke(target, args);
	           		//这里可以做一些事情 ，例如跳完的事后工作
	           		System.out.println("我正在卸妆");
                    return null;
               }
			);
	}
}
