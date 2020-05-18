package staticproxy;

public class UserServiceProxy implements IUserService{

	private IUserService target;
	
	public UserServiceProxy(IUserService target) {
		this.target = target;
	}
	
	
	@Override
	public void dance() {
		//这里可以做一些事情，例如跳舞的准备工作
		System.out.println("我正在化妆");
		//调用方法
		this.target.dance();
		//这里可以做一些事情 ，例如跳完的事后工作
		System.out.println("我正在卸妆");
	}
	
}
