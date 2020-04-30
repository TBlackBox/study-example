package jdk8time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.*;

public class DurationTest {

	public static void main(String[] args) {
		LocalDateTime from = LocalDateTime.of(2020, Month.JANUARY, 10, 12, 0, 0);   //2020-01-10 12:00:00
		LocalDateTime to = LocalDateTime.of(2020, Month.FEBRUARY, 10, 12, 10, 10);     // 2020-02-10 12:10:10
		Duration duration = Duration.between(from, to);     // 表示从2020-01-10 12:00:00 到2020-02-10 12:10:10 这段时间

		// 这段时间的总天数    值：31
		long days = duration.toDays();
		// 这段时间的小时数  值：744
		long hours = duration.toHours(); 
		 // 这段时间的分钟数 值:44650
		long minutes = duration.toMinutes(); 
		 // 这段时间的秒数 值：2679010
		long seconds = duration.getSeconds(); 
		// 这段时间的毫秒数 值：2679010000
		long milliSeconds = duration.toMillis();   
		// 这段时间的纳秒数 值：2679010000000000
		long nanoSeconds = duration.toNanos();      
		System.out.println(days);
		System.out.println(hours);
		System.out.println(minutes);
		System.out.println(seconds);
		System.out.println(milliSeconds);
		System.out.println(nanoSeconds);
		
		// 10天  
		Duration duration1 = Duration.of(10, ChronoUnit.DAYS);   
		// 10000毫秒
		Duration duration2 = Duration.of(10000, ChronoUnit.MILLIS);  
		System.out.println(Duration.ofDays(2).toHours());
		
		Period period = Period.of(4, 5, 23);
		
		int year = period.getYears();
		//值：4
		System.out.println(year);
		Period minPeriod = period.minusYears(2);
		//值：2
		System.out.println(minPeriod.getYears());
		
		LocalDate date = LocalDate.now();
		// 返回下一个距离当前时间最近的星期日
		LocalDate date7 = date.with(nextOrSame(DayOfWeek.SUNDAY));   
		// 返回本月最后一个星期六
		LocalDate date9 = date.with(lastInMonth(DayOfWeek.SATURDAY));  

	}
}
