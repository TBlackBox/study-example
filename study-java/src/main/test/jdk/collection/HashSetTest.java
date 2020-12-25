package jdk.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class HashSetTest {

	public static void main(String[] args) {
		
		HashSet set= new HashSet();
		
		LinkedHashSet linkHashSet = new LinkedHashSet<String>();
		
		ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
		
		
		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
		
		ConcurrentSkipListMap<String, String> skipListMap = new ConcurrentSkipListMap<String, String>();
	
		
		boolean a[] = new boolean[5];
		System.out.println(Arrays.toString(a));
		
//		for(int i =0; i < 3; i++) {
//			for(int j = 0; j < 3; j++) {
//				if(j == 1) {
//					continue;
//				}else {
//					System.out.println("i=" + i + ", j=" + j);
//				}
//			}
//		}
		
	}
}
