package jdk8annotation;

public class TestRepeatJdk8Before {

	@MyAnnotationRepeatJdk8Befores({@MyAnnotationRepeatJdk8Before("么么哒"),@MyAnnotationRepeatJdk8Before("思密达")})
	public void method() {
	}
}
