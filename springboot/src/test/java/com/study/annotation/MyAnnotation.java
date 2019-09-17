package com.study.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) 
// @Target 目标对象是类型 作用：描述该注解修饰的范围，可被用于 packages、
//types（类、接口、枚举、Annotation类型）、
//类型成员（方法、构造方法、成员变量、枚举值）、
//方法参数和本地变量（如循环变量、catch参数）。
// 值为 ElementType 里面的 枚举值

@Retention(RetentionPolicy.RUNTIME) 
//保存至运行时
//作用：描述该注解的生命周期，表示在什么编译级别上保存该注解的信息。
//Annotation被保留的时间有长短：某些Annotation仅出现在源代码中，而被编译器丢弃；
//而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
//而另一些在class被装载时将被读取（请注意并不影响class的执行，
//因为Annotation与class在使用上是被分离的）。
//3 个取值的作用
//1.SOURCE:在源文件中有效（即源文件保留）
//2.CLASS:在class文件中有效（即class保留）
//3.RUNTIME:在运行时有效（即运行时保留）

@Documented 
//生成javadoc文档时，该注解内容一起生成文档

@Inherited 
//作用：@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
//如果一个使用了@Inherited修饰的annotation类型被用于一个class，
//则这个annotation将被用于该class的子类。
public @interface MyAnnotation {

	 	public String value() default ""; //当只有一个元素时，建议元素名定义为value(),这样使用时赋值可以省略"value="
	 	
	    String name() default "老王"; //String
	    
	    int age() default 20; //int
	    
	    boolean isStudent() default true; //boolean
	    
	    String[] alias(); //数组
	    
	    enum Color {
	    	GREEN, 
	    	BLUE, 
	    	RED,
	    } //枚举类型
	    
	    Color favoriteColor() default Color.GREEN; //枚举值
}

