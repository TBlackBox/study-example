package optional;

import java.util.Optional;

public class testOptional {

	
	/*
	 * 一、Optional 容器类：用于尽量避免空指针异常
	 * 	Optional.of(T t) : 创建一个 Optional 实例
	 * 	Optional.empty() : 创建一个空的 Optional 实例
	 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
	 * 	isPresent() : 判断是否包含值
	 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
	 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
	 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
	 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
	 */
	public static void main(String[] args) {
		
		//Optional.of(T t) : 创建一个 Optional 实例   t不能为空
		Optional<String> op = Optional.of("哈哈");
		
		//创建一个空的 Optional 实例
		Optional<String> op1 = Optional.empty();
		
		//Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
		Optional<String> op2 = Optional.ofNullable("wewrrr");
		
		boolean b = op.isPresent();
		
		//orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
		String op4 = op.orElse("没有值时我返回");
		
		//orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值 Supplier 供给型
		String op5 = op.orElseGet(() -> {
			String a = "王麻子";
			return a;
		});
		
		//map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
		Optional<String> op6 = op.map(String::toLowerCase);
		
		//flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
		Optional<String> op7 = op.flatMap((e) -> Optional.of("么么哒"));
	}
}
