package defaultInterfaceMethod;

public interface JDK8AfterInteface2 {
	//static 修饰符定义静态方法
	static void staticMethod() {
		System.out.println("接口2中的方法");
	}
	
	//default 修饰符定义默认方法
	default void defalutMethod() {
		System.out.println("接口2中的默认方法");
	}
}
