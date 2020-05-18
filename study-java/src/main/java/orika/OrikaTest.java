package orika;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaTest {

	public static void main(String[] args) {
		noEntityCopy();
	}
	
	//不同实体  不同属性拷贝  可以自定义
	
	public static void noEntityCopy() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(User.class, Admin.class)
					 .field("age", "id")
					 .byDefault()
					 .register();
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		
		User user = new User();
		user.setName("老王");
		user.setAge(52);
		
		Admin admin = mapperFacade.map(user, Admin.class);
		System.out.println(user);
		System.out.println(admin);
		System.out.println(user.hashCode());
		System.out.println(admin.hashCode());
	}
	
	//相同实体的拷贝
	public static void commonEntityCopy() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		
		User user = new User();
		user.setName("老王");
		user.setAge(52);
		
		User copyUser = mapperFacade.map(user, User.class);
		System.out.println(user);
		System.out.println(copyUser);
		System.out.println(user.hashCode());
		System.out.println(copyUser.hashCode());
	}
	
	//不同实体相同属性的拷贝
	public static void noEntityCommonAttr() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(User.class, Admin.class);
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		
		User user = new User();
		user.setName("王麻子");
		user.setAge(25);
		
		Admin admin = mapperFacade.map(user, Admin.class);
		
		System.out.println(admin);
		System.out.println(user);
	}
}
