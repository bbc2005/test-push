package com.lzg.booktest.t4.s1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** 方法lockInterruptibly()、tryLock()和tryLock(long timeout,TimmeUnit,unit)
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年9月6日 下午10:19:33 
 */
public class LockInterruptibleyTest1 {
	/**
	 * void lockInterruptibly()的作用：如果当前线程未被中断，
	 * 则获取锁定，如果已经被中断则出现异常。
	 * @param args
	 * 2017年9月6日 下午10:20:49
	 * @throws InterruptedException 
	 */
	public static void main1(String[] args) throws InterruptedException {
		final litService1 service = new litService1();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread.sleep(500);
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
		
		b.interrupt();//打标记
		System.out.println("main end !");
	}
	/**
	 * boolean tryLock()的作用：仅在调用时锁定未被另一个线程保持的
	 * 情况下，才获取该锁定。
	 * @param args
	 * 2017年9月6日 下午10:40:56
	 */
	public static void main2(String[] args) {
		final LitService2 service = new LitService2();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
	}
	/**
	 * boolean tryLock(long timeout,TimeUnit unit)的作用：
	 * 如果锁定在给定等待时间内没有被另一个线程保持，且当前线程未被中断，
	 * 则获取该锁定。
	 * @param args
	 * 2017年9月6日 下午10:47:09
	 */
	public static void main(String[] args) {
		final LitService3 service = new LitService3();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "调用waitMethod时间：" + System.currentTimeMillis());
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
	}
}
class litService1{
	public ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println("catch----------");
				e.printStackTrace();
			}
//			lock.lock();//正常结束，换成lock.lockInterruptibly()出异常
			System.out
					.println("lock begin " + Thread.currentThread().getName());
			for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
				String newString = new String();
				Math.random();
			}
			System.out.println("lock  end " + Thread.currentThread().getName());
		} finally{
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}
}
class LitService2{
	public ReentrantLock lock = new ReentrantLock();
	public void waitMethod(){
		if(lock.tryLock()){
			System.out.println(Thread.currentThread().getName() + "获得锁");
		}else{
			System.out.println(Thread.currentThread().getName() + "没有获得锁");
		}
	}
}

class LitService3{
	public ReentrantLock lock = new ReentrantLock();
	public void waitMethod(){
		try {
			if (lock.tryLock(3, TimeUnit.SECONDS)) {
				System.out.println("      " + Thread.currentThread().getName()
						+ " 获得锁的时间：" + System.currentTimeMillis());
				Thread.sleep(10000);
			} else {
				System.out.println("      " + Thread.currentThread().getName()
						+ " 没有获得锁");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}
}