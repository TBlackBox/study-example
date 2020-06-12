package jdk;

import java.io.Serializable;

public class User implements Serializable{
	
	private String name;
	
	private static String school = "四川大学";
	
	private transient Integer age;

	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getSchool() {
		return school;
	}

	public static void setSchool(String school) {
		User.school = school;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
