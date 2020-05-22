package gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SimpleTestMain {

	public static void main(String[] args) {
		//simple();
		new SimpleTestMain().objectTest();
	}
	
	public static void simple() {
		//基本类型序列化
		Gson gson = new Gson();
		gson.toJson(1); // 1       
		gson.toJson("abcd"); //"abcd"      
		gson.toJson(new Long(10));// 10
		gson.toJson(new int[]{1,2,3,4});  //[1,2,3,4]
		gson.toJson(new String[] {"aa","bb","cc"}); //["aa","bb","cc"]

		// 基本类型反序列化
		int one = gson.fromJson("1", int.class);  //1
		Integer one1 = gson.fromJson("1", Integer.class); //1
		Long one2 = gson.fromJson("1", Long.class); //1
		Boolean b = gson.fromJson("false", Boolean.class); //false
		String str = gson.fromJson("\"abc\"", String.class); // abc
		String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class); //["abc"]
	}
	
	public void objectTest() {
		User user = new User("张三", 25, true);
		//序列化对象
		Gson gson = new Gson();
		String userStr = gson.toJson(user);
		//{"name":"张三","age":25,"isBoy":true}
		System.out.println(userStr);
		
		//反序列化
		User user2 = gson.fromJson(userStr, User.class);
	}
	
	public class User{
		private String name;
		private Integer age;
		private Boolean isBoy;
		public User(String name,Integer age,Boolean isBoy) {
			this.age = age;
			this.name = name;
			this.isBoy = isBoy;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public Boolean getIsBoy() {
			return isBoy;
		}
		public void setIsBoy(Boolean isBoy) {
			this.isBoy = isBoy;
		}
		
		
	}
	
	public void collection() {
		Gson gson = new Gson();
		Collection<Integer> ints = new ArrayList();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		ints.add(4);
		ints.add(5);
		
		//序列换
		String json = gson.toJson(ints);  // [1,2,3,4,5]

		// 反序列化
		Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
		Collection<Integer> ints2 = gson.fromJson(json, collectionType);
	}
}
