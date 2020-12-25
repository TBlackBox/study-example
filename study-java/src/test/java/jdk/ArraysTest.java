package jdk;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ArraysTest {

	@Test
	public void testArrays() {
		
		System.out.println(Integer.toBinaryString(-1));
		
//		int[] array = {1,2,3,4};
//		List<Integer> list = Arrays.asList(1,2,3,4);
//		list.forEach((item) ->{
//			System.out.println(item);
//		});
	}
	
	
	@Test
	public void testSort() {
		Integer[] src = {2,1,2,2,32,12,12};
		Arrays.sort(src);
		
		
		//对整个数组排序 输出：[1, 2, 2, 2, 12, 12, 32]
		System.out.println(Arrays.toString(src));
		
		int[] array = {32,43,21,23,12};
		Arrays.sort(array,1,4);
		//对指定索引下排序  输出： [32, 21, 23, 43, 12]
		System.out.println(Arrays.toString(array));
		
		int[] pallArr = {12,221,112,11,21,212,321,312,12,12,12,231,1212,31};
		Arrays.parallelSort(pallArr);
		//从小到大的排序 输出：[11, 12, 12, 12, 12, 21, 31, 112, 212, 221, 231, 312, 321, 1212]
		System.out.println(Arrays.toString(pallArr));
		
		Integer[] comparatorArr = {23,12,32,321,1234,21,23,42,12,3,4};
		

		//上面的都是自然顺序  如果要大到小排列 可以这样写，这里使用到了策略模式，这样非常方便
		//传统写法
//		Arrays.sort(comparatorArr, new DifineComparator());
		//表达式的写法
		Arrays.sort(comparatorArr, (o1,o2) ->{
//			返回值为正值，把o1排在o2后面；
//			返回值为负值，把o1排在o2前面。
//			如果返回值是0，按照容器之前的顺序排列。
			return o2 -o1;
		});
		//输出 [1234, 321, 42, 32, 23, 23, 21, 12, 12, 4, 3]
		System.out.println(Arrays.toString(comparatorArr));
	}
	
	class DifineComparator implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
		
	}
	
	
	@Test
	public void testBinarySearch() {
		Integer[] a = {122,222,12,221,2,13,75654,321,128,12};
		
		//将数值进行升序排序(这里必须进行升序排序)
		Arrays.sort(a);
		//[2, 12, 12, 13, 122, 128, 221, 222, 321, 75654]
		// 0   1  2    3   4    5   6    7    8    9
		System.out.println(Arrays.toString(a));
		
		int index = Arrays.binarySearch(a, 321);
		System.out.println(index); //8
		int xindex = Arrays.binarySearch(a, 50);
		//没找到返回负数  看源码 返回的就是最后的低位+1即  -（low + 1）
		System.out.println(xindex); //-4
		int yindex = Arrays.binarySearch(a, 12);
		System.out.println(yindex); //1
	}
	
	@Test
	public void testCopy() {
		
		int[] src = {211,21,321};
		
		int[] dest = Arrays.copyOf(src, src.length + 1);
		//输出 [211, 21, 321, 0]
		System.out.println(Arrays.toString(dest));
		
		int[] dest2 = Arrays.copyOf(src, src.length - 1);
		//输出 [211, 21]
		System.out.println(Arrays.toString(dest2));
		
		int[] dest3 = Arrays.copyOfRange(src, 1,2);
		//输出 [21]
		System.out.println(Arrays.toString(dest3));
	}
	
}
