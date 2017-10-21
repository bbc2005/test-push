package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月8日 下午9:49:47 
 */
public class JionDemo {
	public static void main(String[] args) throws InterruptedException {
		JionTest j = new JionTest();
		
		Thread t1 = new Thread(j);
		Thread t2 = new Thread(j);
		
		t1.start();
		
//		t1.join();//t1线程要申请加入进来，运行。临时加入一个线程运算时可以使用join方法。
		
		t2.start();
		
//		t2.setPriority(Thread.MAX_PRIORITY);
		
		for (int i = 0; i < 50; i++) {
//			System.out.println(Thread.currentThread().getName() + "......" + i);
//			System.out.println(Thread.currentThread() + "......" + i);
		}
	}
}
class JionTest implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
//			System.out.println(Thread.currentThread().getName() + "......" + i);
			System.out.println(Thread.currentThread().toString() + "......" + i);
			Thread.yield();//暂停当前正在执行的线程对象，并执行其他线程。
		}
	}
	
}
