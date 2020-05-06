package defaultInterfaceMethod;

public class MethodTest {

	public static void main(String[] args) {
		//静态方法： 必须通过接口调用
		JDK8AfterInteface.staticMethod();
		//defalut方法：必须通过接口实现类调用
		new JDK8AfterIntefaceImpl().defalutMethod();
		
		JDK8AfterInteface.staticMethod();
		JDK8AfterInteface2.staticMethod();
		new JDKAfterIntefaceImpl2().defalutMethod();
	}
}
