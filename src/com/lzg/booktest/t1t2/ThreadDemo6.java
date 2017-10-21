package com.lzg.booktest.t1t2;

import java.util.Random;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午3:18:42 
 */
public class ThreadDemo6 {
	/**
	 * 线程优先级的继承特性 
	 * @param args
	 * 2017年8月13日 下午3:41:17
	 */
	public static void main1(String[] args) {
		System.out.println("main thread begin priority=" + Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(6);
		System.out.println("main thread end priority=" + Thread.currentThread().getPriority());
		ThreadTest6 t = new ThreadTest6();
		t.start();
	}
	/**
	 * 优先级具有规律性
	 * 也就是CPU尽量将执行资源让给优先级比较高的线程。
	 * @param args
	 * 2017年8月13日 下午3:39:30
	 */
	public static void main2(String[] args) {
		for (int i = 0; i < 5; i++) {
			ThreadTest62 t1 = new ThreadTest62();
			t1.setPriority(10);
			t1.start();
			ThreadTest63 t2 = new ThreadTest63();
			t2.setPriority(1);
			t2.start();
		}
	}
	/**
	 * 优先级具有随机性
	 * 优先级较高的线程不一定每一次都先执行完。
	 * 线程的优先级与运行结果的顺序无关，他们的关系具有不确定性和随机性。
	 * @param args
	 * 2017年8月13日 下午3:45:32
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			ThreadTest64 t1 = new ThreadTest64();
			t1.setPriority(5);
			t1.start();
			
			ThreadTest65 t2 = new ThreadTest65();
			t2.setPriority(6);
			t2.start();
		}
	}
}

class ThreadTest6 extends Thread{
	@Override
	public void run() {
		System.out.println("ThreasTest6 run priority=" + this.getPriority());
		ThreadTest61 t = new ThreadTest61();
		t.start();
	}
}
class ThreadTest61 extends Thread{
	@Override
	public void run() {
		System.out.println("ThreadTest61 run priority=" + this.getPriority());
	}
}
class ThreadTest62 extends Thread{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		long addResult = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50000; j++) {
				Random random = new Random();
				random.nextInt();
				addResult = addResult + i;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("*******ThreadTest62 use time="+ (endTime - beginTime));
	}
	
}
class ThreadTest63 extends Thread{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		long addResult = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50000; j++) {
				Random random = new Random();
				random.nextInt();
				addResult = addResult + i;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("++++++++ThreadTest63 use time="+ (endTime - beginTime));
	}
	
}
class ThreadTest64 extends Thread{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("------------ThreadTest64 use time=" + (endTime - beginTime));
	}
	
}
class ThreadTest65 extends Thread{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("====================ThreadTest65 use time=" + (endTime - beginTime));
	}
	
}