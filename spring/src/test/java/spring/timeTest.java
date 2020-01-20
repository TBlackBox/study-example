package spring;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;

import org.junit.Test;

public class timeTest {

	//����ʱ��
	@Test
	public void test() {
		
		//ͨ��now()��ȡ
		LocalDateTime localDateTime = LocalDateTime.now();
		//ͨ��of()��ȡ  ���     2020-01-13T12:41:00.007
		System.out.println(localDateTime);
		
		//of(����) �ж����̬���������Ը����Լ�����Ҫѡ��
		LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 21, 2, 21);
		//���   2020-01-21T02:21
		System.out.println(localDateTime2);
		
	}
	
	@Test
	public void plusTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("��ǰʱ�䣺"+localDateTime);
		
		/**
		 * ���ʱ��
		 */
		//ע��  ������Ҫһ���±���������  LocalDateTime �ǲ��ɱ��ʵ��
		//���кܶ෽��  ����ֻ��ʾ�����Сʱ  ��  �� ��  ����  ����  ����Щ������
		LocalDateTime localDateTime1 = localDateTime.plusHours(2);
		System.out.println("���2��Сʱ���ʱ��:"+localDateTime1);
		
		/**
		 * ����ʱ��
		 */
		//�ڵ�ǰʱ�����2����  ������ʾ������  ��Ȼ������ ��  �� �ֵȵ�  ���忴api
		LocalDateTime localDateTime2 =  localDateTime.minusMonths(2);
		//���   ��ǰʱ�����2���µ�ʱ�䣺2019-11-13T13:04:37.199
		System.out.println("��ǰʱ�����2���µ�ʱ�䣺" + localDateTime2);
		
		/**
		 * ָ��ʱ��
		 */
		//����ָ�����е���  ��Ȼ  ����ָ�� �� ��  ����Щ  
		LocalDateTime localDateTime3 = localDateTime.withDayOfMonth(2);
		//��� �����µ���ָ��Ϊ2�ţ�2020-01-02T13:10:29.856
		System.out.println("�����µ���ָ��Ϊ2�ţ�"+localDateTime3);
	}
	
	//��ȡʱ��
	@Test
	public void getTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("ʱ�䣺"+localDateTime);
		
		System.out.println("�õ����е��죺"+localDateTime.getDayOfMonth());
		System.out.println("�õ����е��죺"+localDateTime.getDayOfYear());
		System.out.println("�õ�Сʱ��"+localDateTime.getHour());
		System.out.println("�õ���������"+localDateTime.getMinute());
		System.out.println("�õ����£�ֵ����"+localDateTime.getMonthValue());
		System.out.println("�õ����룺"+localDateTime.getNano());
		System.out.println("�õ��룺"+localDateTime.getSecond());
		System.out.println("�õ����ڼ���Ӣ�ģ���"+localDateTime.getDayOfWeek());
		System.out.println("�õ��·ݣ�Ӣ�ģ���"+localDateTime.getMonth());
		//���
//		ʱ�䣺2020-01-20T15:02:39.535
//		�õ����е��죺20
//		�õ����е��죺20
//		�õ�Сʱ��15
//		�õ���������2
//		�õ����£�ֵ����1
//		�õ����룺535000000
//		�õ��룺39
//		�õ����ڼ���Ӣ�ģ���MONDAY
//		�õ��·ݣ�Ӣ�ģ���JANUARY
	}
	
	//�Ƚ�ʱ��
	@Test
	public void compareTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("ʱ��1��" + localDateTime);
		LocalDateTime localDateTime2 = localDateTime.plusHours(2);
		System.out.println("ʱ��2��" + localDateTime2);
		
		System.out.println("ʱ��2 ��ʱ��1֮��" + localDateTime.isAfter(localDateTime2));
		System.out.println("ʱ��2 ��ʱ��1֮ǰ��" + localDateTime.isBefore(localDateTime2));
//		ʱ��1��2020-01-20T15:13:35.551
//		ʱ��2��2020-01-20T17:13:35.551
//		ʱ��2 ��ʱ��1֮��false
//		ʱ��2 ��ʱ��1֮ǰ��true
		
		//isEqual()  �ж�ʱ���Ƿ���� ��� flase
	    System.out.println(localDateTime.isEqual(localDateTime2)); 
	}
	
	@Test
	public void instant1() {
		//��� ��ǰʱ��:2020-01-20T16:00:31.661
		System.out.println("��ǰʱ��:" + LocalDateTime.now()); 
		Instant instant = Instant.now(); //Ĭ��ʹ�� UTC ʱ��
		// ���  instant��2020-01-20T07:58:44.388Z   �ر�ע�� �� �����ʱ��͵�ǰʱ�����8��Сʱ�� �����õ���UTC ʱ��
		System.out.println("instant��" + instant);
		
		//�õ����ʱ���   ���  1579507124
		System.out.println(instant.getEpochSecond());
		//�õ��������ʱ���  ���  1579507124388
		System.out.println(instant.toEpochMilli());
		//�õ����� ��� 388000000
		System.out.println(instant.getNano());
	}
	
	@Test
	public void instant2() {
	
	}
	
	}
	
}
