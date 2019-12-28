package hello;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestlambdaRef {

	public static void main(String[] args) {
		
		//test4();
		//Stream.of("1");
		//Stream<T>  
		//Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(3);
		//stream3.forEach(System.out::println);
		
		Stream<Double> stream = Stream.generate(Math::random);
		stream.forEach(System.out::println);
	}
	
	//数组引用
	public static void test4() {
		
		//正常的声明一个数组
		Function<Integer, String[]> fun = (args) -> new String[args];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		System.out.println("--------------------------");
		
		//通过数组引用获取一个用户数组
		Function<Integer, User[]> fun2 = User[] :: new;
		User[] users = fun2.apply(20);
		System.out.println(users.length);
	}
	
	//构造器参数引用
	public static void test3() {
		Supplier<User> sup = () -> new User();
		User user = sup.get();
		
		//改造一哈赛  调用的是无参数够着器 Supplier是不穿参数的，只有返回值
		Supplier<User> sup1 = User::new;
		User user1 = sup1.get();
		
		//这个就是调用的有2参数的构造器了 BiFunction可以看做是Function的一个子接口
		BiFunction<String, Integer, User> user2 = User::new;
	}
	
	public static void test2() {
		//看两个字符串是否相等
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		System.out.println(bp.test("www", "sss"));
		
		System.out.println("-----------------------------------------");
		//类名::方法名的形式改装   需要满足的条件  第一个参数是调用者，第二个参数是方法的参数,第二个参数可以没有
		BiPredicate<String, String> bp2 = String::equals;
		System.out.println(bp2.test("qw", "wq"));
	}
	
	
	public static void test1() {
		Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
		System.out.println(com.compare(3, 2));
		
		//静态方法::方法名的形式改写   保证方法的参数跟接口的里面方法的参数一样
		Comparator<Integer> com1 = Integer::compare;
		System.out.println(com1.compare(3, 2));
		
	}
	
	
	public static void test() {
		User user = new User("张三", 25);
		//获取名字
		Supplier<String> sup = () -> user.getName();
		System.out.println("直接获取值："+ sup.get());
		
		//通过方法引用可以这样写,这就是  实例对象::方法名
		Supplier<String> sup1 = user::getName;
		System.out.println("通过方法引用获取值："+ sup.get());
		//直接获取值：张三
		//通过方法引用获取值：张三
	}
	
}
