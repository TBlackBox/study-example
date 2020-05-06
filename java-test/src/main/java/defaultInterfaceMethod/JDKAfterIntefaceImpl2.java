package defaultInterfaceMethod;

public class JDKAfterIntefaceImpl2 implements JDK8AfterInteface, JDK8AfterInteface2 {
	//实现的两个接口种都有这个默认方法，这里必须覆盖，不然编译要报错
	@Override
	public void defalutMethod() {
		System.out.println("覆盖接口的默认方法");
	}
}
