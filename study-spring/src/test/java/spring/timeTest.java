package spring;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class timeTest {

	@Test
	public void testLocalDate() { 
		System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
	}
	
	//创建时间
	@Test
	public void test() { 
		
		//通过now()获取
		LocalDateTime localDateTime = LocalDateTime.now();
		//通过of()获取  输出     2020-01-13T12:41:00.007
		System.out.println(localDateTime);
		
		//of(参数) 有多个静态方法，可以根据自己的需要选择
		LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 21, 2, 21);
		//输出   2020-01-21T02:21
		System.out.println(localDateTime2);
		
	}
	
	@Test
	public void plusTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间："+localDateTime);
		
		/**
		 * 添加时间
		 */
		//注意  这里需要一个新变量来接收  LocalDateTime 是不可变的实例
		//还有很多方法  这里只演示了添加小时  分  天 秒  毫秒  纳秒  周这些都可以
		LocalDateTime localDateTime1 = localDateTime.plusHours(2);
		System.out.println("添加2个小时候的时间:"+localDateTime1);
		
		/**
		 * 减少时间
		 */
		//在当前时间减少2个月  这里演示的是月  当然还有年 天  秒 分等等  具体看api
		LocalDateTime localDateTime2 =  localDateTime.minusMonths(2);
		//输出   当前时间减少2个月的时间：2019-11-13T13:04:37.199
		System.out.println("当前时间减少2个月的时间：" + localDateTime2);
		
		/**
		 * 指定时间
		 */
		//这里指定月中的天  当然  还能指定 年 月  日这些  
		LocalDateTime localDateTime3 = localDateTime.withDayOfMonth(2);
		//输出 将改月的天指定为2号：2020-01-02T13:10:29.856
		System.out.println("将改月的天指定为2号："+localDateTime3);
	}
	
	//获取时间
	@Test
	public void getTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("时间："+localDateTime);
		
		System.out.println("得到月中的天："+localDateTime.getDayOfMonth());
		System.out.println("得到年中的天："+localDateTime.getDayOfYear());
		System.out.println("得到小时："+localDateTime.getHour());
		System.out.println("得到分钟数："+localDateTime.getMinute());
		System.out.println("得到几月（值）："+localDateTime.getMonthValue());
		System.out.println("得到纳秒："+localDateTime.getNano());
		System.out.println("得到秒："+localDateTime.getSecond());
		System.out.println("得到星期几（英文）："+localDateTime.getDayOfWeek());
		System.out.println("得到月份（英文）："+localDateTime.getMonth());
		//输出
//		时间：2020-01-20T15:02:39.535
//		得到月中的天：20
//		得到年中的天：20
//		得到小时：15
//		得到分钟数：2
//		得到几月（值）：1
//		得到纳秒：535000000
//		得到秒：39
//		得到星期几（英文）：MONDAY
//		得到月份（英文）：JANUARY
	}
	
	//比较时间
	@Test
	public void compareTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("时间1：" + localDateTime);
		LocalDateTime localDateTime2 = localDateTime.plusHours(2);
		System.out.println("时间2：" + localDateTime2);
		
		System.out.println("时间2 在时间1之后：" + localDateTime.isAfter(localDateTime2));
		System.out.println("时间2 在时间1之前：" + localDateTime.isBefore(localDateTime2));
//		时间1：2020-01-20T15:13:35.551
//		时间2：2020-01-20T17:13:35.551
//		时间2 在时间1之后：false
//		时间2 在时间1之前：true
		
		//isEqual()  判断时间是否相等 输出 flase
	    System.out.println(localDateTime.isEqual(localDateTime2)); 
	}
	
	@Test
	public void instant1() {
		//输出 当前时间:2020-01-20T16:00:31.661
		System.out.println("当前时间:" + LocalDateTime.now()); 
		Instant instant = Instant.now(); //默认使用 UTC 时区
		// 输出  instant：2020-01-20T07:58:44.388Z   特别注意 ： 这里的时间和当前时间相差8个小时， 这里用的是UTC 时间
		System.out.println("instant：" + instant);
		
		//得到秒的时间搓   输出  1579507124
		System.out.println(instant.getEpochSecond());
		//得到带毫秒的时间搓  输出  1579507124388
		System.out.println(instant.toEpochMilli());
		//得到纳秒 输出 388000000
		System.out.println(instant.getNano());
	}
	
	@Test
	public void instant2() {
	}
}	
