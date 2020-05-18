package jdk8hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk8 HashMap新增的方法测试
 * @author Administrator
 *
 */
public class JDK8HashMapTest {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		//初始化一些值
		map.put("a", 1);
		map.put("b",null);
		map.put("c",3);
		
		//当key对应的value没有时才放入新的值，可以防止旧值被覆盖
		map.putIfAbsent("a", 2);
		
		//映射当key不存在或value为null时的value值，包括创建对应key，返回value,如果key存在，则不做处理
		map.computeIfAbsent("d", (v) -> 36);
		
		//与上述的computeIfAbsent 相反，当key存在时计算替换相应的value，并返回。当key不存在时返回null
		Integer s = map.computeIfPresent("f", (k,v) -> v * 2);
		
		//计算key的value，如果key不存在，则创建key并赋值value，如果value为null，则报NPE
		map.compute("f", (k,v) -> 52);
		
		//value 为null 报NPE
		//如果key存在，且value 不为null，新，老value根据计算得出结果，赋值给key。
		//如果key不存在，且value 不为null，value赋值给key。
		map.merge("h", 2, (v1,v2) -> v1 + v2);
		
		//如果key为空，就返回默认值，否则返回value
		Integer retV = map.getOrDefault("f", 12);
		System.out.println(retV);
		
		//替换  可以判断oldValue是否正确
		map.replace("a", 2, 23);
		//用map的put(相同的key)也能做到newValue替换oldValue，但是replace走的是get获取的流程，获取不到就直接返回false。
		//-- replace 所在场景一般是key存在的
		map.replace("b", 12);
		
		//根据键值移除  如果值不匹配 将移除不成功
		map.remove("b",121);
		
		//遍历map
		map.forEach((k,v) -> {
			System.out.println("键："+k+ "  值："+v);
		});
		
		ConcurrentHashMap map2 = new ConcurrentHashMap<String, Integer>();
		map2.put("aa", 11);
		Set<String> keySet = map2.keySet();
		keySet.forEach(e -> {
			System.out.println(e);
		});
	}
}
