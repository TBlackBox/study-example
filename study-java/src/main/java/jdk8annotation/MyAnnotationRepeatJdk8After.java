package jdk8annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@Repeatable(MyAnnotationRepeatJdk8Afters.class)
public @interface MyAnnotationRepeatJdk8After {
	String value();
}
