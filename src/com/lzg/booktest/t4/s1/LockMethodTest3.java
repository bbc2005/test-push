package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.ReentrantLock;

/** 方法isFair()，isHeldByCurrentThread()和isLocked()的测试
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年9月5日 下午10:45:52 
 */
public class LockMethodTest3 {
	/**
	 * boolean isFair()的作用是判断是不是公平锁
	 * 在默认的情况下，ReentrantLock类使用的是非公平锁。
	 * @param args
	 * 2017年9月5日 下午10:47:18
	 */
	public static void main1(String[] args) {
		final Lm3Service1 service1 = new Lm3Service1(true);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service1.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
		final Lm3Service1 service2 = new Lm3Service1(false);
		
		runnable = new Runnable() {
			@Override
			public void run() {
				service2.serviceMethod();
			}
		};
		thread = new Thread(runnable);
		thread.start();
		
	}
	/**
	 * boolean isHeldByCurrentThread()的作用是查询当前线程
	 * 是否保持此锁定。
	 * @param args
	 * 2017年9月5日 下午10:55:45
	 */
	public static void main2(String[] args) {
		final Lm3Service2 service1 = new Lm3Service2(true);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service1.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
	/**
	 * boolean isLocked()的作用是查询此
	 * 锁定是否由任意线程保持。
	 * @param args
	 * 2017年9月5日 下午11:03:12
	 */
	public static void main(String[] args) {
		final Lm3Service3 service = new Lm3Service3(true);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
class Lm3Service1{
	private ReentrantLock lock;

	public Lm3Service1(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	public void serviceMethod(){
		try {
			lock.lock();
			System.out.println("公平锁情况：" + lock.isFair());
		} finally {
			lock.unlock();
		}
	}
}

class Lm3Service2{
	private ReentrantLock lock;

	public Lm3Service2(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod(){
		try {
			System.out.println(lock.isHeldByCurrentThread());
			lock.lock();
			System.out.println(lock.isHeldByCurrentThread());
		} finally{
			lock.unlock();
		}
	}
	
}
class Lm3Service3{
	private ReentrantLock lock;

	public Lm3Service3(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod(){
		try {
			System.out.println(lock.isLocked());
			lock.lock();
			System.out.println(lock.isLocked());
		} finally {
			lock.unlock();
		}
	}
	
}