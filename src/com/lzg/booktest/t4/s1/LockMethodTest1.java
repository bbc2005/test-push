package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 方法getHoldCount()、getQueueLength()和getWaitQueueLength()的测试
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月31日 下午10:19:40 
 */
public class LockMethodTest1 {

	/**
	 * int getHoldCount()的作用是查询当前线程保持
	 * 此锁定的个数，也就是调用lock()方法的次数。
	 * @param args
	 * 2017年8月31日 下午10:20:59
	 */
	public static void main1(String[] args) {
		LmtService1 service = new LmtService1();
		service.serviceMethod1();
	}
	/**
	 * 方法int getQueueLength()的作用是
	 * 返回正等待获取此锁定的线程估计数。
	 * @param args
	 * 2017年8月31日 下午10:41:55
	 * @throws InterruptedException 
	 */
	public static void main2(String[] args) throws InterruptedException {
		final LmtService2 service = new LmtService2();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.serviceMethod1();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
			threadArray[i].start();
		}
		Thread.sleep(2000);
		System.out.println("有线程数：" + service.lock.getQueueLength() + "在等待获取锁！");
	}
	/**
	 * 方法 int getWaitQueueLength(Condition condition)的作用是
	 * 返回等待与此锁定相关的给定条件Condition的线程估计数。
	 * @param args
	 * 2017年8月31日 下午10:51:46
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final LmtService3 service = new LmtService3();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
			threadArray[i].start();
		}
		Thread.sleep(2000);
		service.notityMethod();
	}

}
class LmtService1{
	private ReentrantLock lock = new ReentrantLock();
	public void serviceMethod1(){
		try {
			lock.lock();
			System.out.println("serviceMethod1 getHoldCount="
					+ lock.getHoldCount());
			serviceMethod2();
		} finally{
			lock.unlock();
		}
	}
	public void serviceMethod2(){
		try {
			lock.lock();
			System.out.println("serviceMethod2 getHoldCount="
					+ lock.getHoldCount());
//			serviceMethod1();
		} finally{
			lock.unlock();
		}
	}
}

class LmtService2{
	public ReentrantLock lock = new ReentrantLock();
	public void serviceMethod1(){
		try {
			lock.lock();
			System.out.println("ThreadName=" + Thread.currentThread().getName()
					+ "进入方法！");
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}
class LmtService3{
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			lock.lock();
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void notityMethod(){
		try {
			lock.lock();
			System.out.println("有" + lock.getWaitQueueLength(condition)
					+ "个线程正在等待condition");
			condition.signal();
		} finally{
			lock.unlock();
		}
	}
}