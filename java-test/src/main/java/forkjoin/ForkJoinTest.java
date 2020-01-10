package forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Long>{

	private static final long serialVersionUID = -3349417846377101047L;

	private Long start;
	private Long end;
	
	//临界值(也就是说当分到10000，任务都不要在分了，也就是递归的结束条件)
	private static final Long THRESHOLD = 10000L; 
	
	public ForkJoinTest(Long start, Long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if(this.end - this.start <= THRESHOLD) {
			//达到条件了  这个就是最小的任务，进行任务逻辑处理
			long sum = 0L;
			for(long i = this.start;i < this.end;i++) {
				sum += i;
			}
			return sum;
		}else {
			//说明还能进行拆分
			long middle = (this.start + this.end)/2;
			//进行递归调用
			ForkJoinTest left = new ForkJoinTest(this.start,middle);
			//拆分  并将线程压入线程队列
			left.fork();
			ForkJoinTest right = new ForkJoinTest(middle+1,this.end);
			right.fork();
			//结果合并
			return left.join() + right.join();
		}
	}
}
