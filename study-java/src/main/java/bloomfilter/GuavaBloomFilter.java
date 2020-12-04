package bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class GuavaBloomFilter {

	public static void main(String[] args) {
			//预计包含的数据量
			int expectedInsertions = 100000000;
			
			//允许的误差值
			double fpp = 0.01;
		
	        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, fpp);
	        for (int i = 0; i < 100000000; i++) {
	            bloomFilter.put(i);
	        }
	        System.out.println(bloomFilter.mightContain(100)); //true
	        System.out.println(bloomFilter.mightContain(100000001)); //false
	    }
}
