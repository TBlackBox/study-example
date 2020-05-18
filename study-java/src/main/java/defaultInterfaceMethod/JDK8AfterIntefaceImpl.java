package defaultInterfaceMethod;

public class JDK8AfterIntefaceImpl implements JDK8AfterInteface{
	@Override
	public void defalutMethod() {
		System.out.println("接口实现类覆盖了接口的默认方法");
	}
}
