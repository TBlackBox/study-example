package staticproxy;

public class TestMain {
	public static void main(String[] args) {
		UserServiceProxy userServiceProxy =	new UserServiceProxy(new UserServiceImpl());
		userServiceProxy.dance();
	}
}
