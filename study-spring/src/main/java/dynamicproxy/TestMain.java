package dynamicproxy;

import staticproxy.IUserService;
import staticproxy.UserServiceImpl;

public class TestMain {
	public static void main(String[] args) {
		IUserService userService = new UserServiceImpl();
		//获取代理对象
		IUserService proxy = (IUserService) new UserProxyFactory(userService).getProxyInstance();
		//执行方法
		proxy.dance();
	}
}
