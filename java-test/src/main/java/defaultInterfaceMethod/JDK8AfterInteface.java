package defaultInterfaceMethod;

public interface JDK8AfterInteface {
	//static 修饰符定义静态方法
	static void staticMethod() {
		System.out.println("接口中的方法");
	}
	
	//default 修饰符定义默认方法
	default void defalutMethod() {
		System.out.println("接口中的默认方法");
	}
}
