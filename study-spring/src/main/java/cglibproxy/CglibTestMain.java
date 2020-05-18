package cglibproxy;

public class CglibTestMain {
	public static void main(String[] args) {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		UserServiceImpl proxy = (UserServiceImpl) new ProxyFactory(userServiceImpl).getProxyInstance();
		proxy.dance();
	}
}
