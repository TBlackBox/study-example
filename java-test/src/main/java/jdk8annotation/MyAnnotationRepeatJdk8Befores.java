package jdk8annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotationRepeatJdk8Befores {
	//@MyAnnotationRepeatJdk8Befores注解作为可以存储多个@MyAnnotationRepeatJdk8Before注解的容器
	MyAnnotationRepeatJdk8Before[] value();
}
