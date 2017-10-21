package com.lzg.booktest.t3.s1;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月23日 下午10:05:10 
 */
public class NotifyDemo {
	/**
	 * 只通知一个线程
	 * notify()一次只随机通知一个线程进行唤醒。
	 * @param args
	 * 2017年8月23日 下午10:11:42
	 * @throws InterruptedException 
	 */
	public static void main1(String[] args) throws InterruptedException {
		Object lock = new Object();
		NdThreadA a = new NdThreadA(lock);
		a.start();
		
		NdThreadB b = new NdThreadB(lock);
		b.start();
		
		NdThreadC c = new NdThreadC(lock);
		c.start();
		
		Thread.sleep(1000);
		
		NotifyThread notifyThread = new NotifyThread(lock);
		notifyThread.start();
	}
	/**
	 * 方法wait(long)的使用
	 * 带一个参数的wait(long)方法的功能是等待某一时间内
	 * 是否有线程对锁进行唤醒，如果超过这个时间则自动唤醒。
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月23日 下午10:30:18
	 */
	public static void main2(String[] args) throws InterruptedException {
		Thread t1 = new Thread(WaitDemo.runnable1);
		t1.start();
		Thread.sleep(3000);
		Thread t2 = new Thread(WaitDemo.runnable2);
		t2.start();
	}
	/**
	 * 通知过早
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月23日 下午10:54:00
	 */
	public static void main3(String[] args) throws InterruptedException {
		FirstNotify run = new FirstNotify();
		
//		Thread.sleep(100);
		Thread a = new Thread(run.runnableA);
		a.start();
		
		Thread b = new Thread(run.runnableB);
		b.start();
	}
	/**
	 * 等待wait的条件发生变化
	 * 在使用wait/notify模式时，当wait等待的条件
	 * 发生了变化，也容易造成程序逻辑的混乱。
	 * @param args
	 * 2017年8月23日 下午10:54:32
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		
		NdAdd add = new NdAdd(lock);
		NdSubtract subtract = new NdSubtract(lock);
		ThreadSubtract st1 = new ThreadSubtract(subtract);
		st1.setName("st1");
		st1.start();
		
		ThreadSubtract st2 = new ThreadSubtract(subtract);
		st2.setName("st2");
		st2.start();
		
		Thread.sleep(1000);
		
		ThreadAdd at = new ThreadAdd(add);
		at.setName("at");
		at.start();
	}
}
class NdService1{
	public void test(Object lock){
		try {
			synchronized (lock) {
				System.out.println("begin wait()  ThreadName="
						+ Thread.currentThread().getName());
				lock.wait();
				System.out.println("end wait() ThreadName="
						+ Thread.currentThread().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class NdThreadA extends Thread{
	private Object lock;

	public NdThreadA(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		NdService1 service = new NdService1();
		service.test(lock);
	}
	
}
class NdThreadB extends Thread{
	private Object lock;

	public NdThreadB(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		NdService1 service = new NdService1();
		service.test(lock);
	}
	
}
class NdThreadC extends Thread{
	private Object lock;

	public NdThreadC(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		NdService1 service = new NdService1();
		service.test(lock);
	}
	
}
class NotifyThread extends Thread{
	private Object lock;

	public NotifyThread(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized(lock){
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
			lock.notifyAll();
		}
	}
	
}

class WaitDemo{
	private static Object lock = new Object();
	static Runnable runnable1 = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("wait begin time="
							+ System.currentTimeMillis());
					lock.wait(5000);
					System.out.println("wait end time="
							+ System.currentTimeMillis());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	static Runnable runnable2 = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("notify begin time="
							+ System.currentTimeMillis());
					lock.notify();
					System.out.println("notify end time="
							+ System.currentTimeMillis());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}

class FirstNotify{
	String lock = new String("");
	boolean isFirstRunB = false;
	Runnable runnableA = new Runnable() {
		
		@Override
		public void run() {
			try {
				synchronized (lock) {
					while(isFirstRunB == false){
						System.out.println("begin wait");
						lock.wait();
						System.out.println("end wait");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	Runnable runnableB = new Runnable() {
		
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("begin notify");
				lock.notify();
				System.out.println("endd notify");
				isFirstRunB = true;
			}
		}
	};
}
class ValueObject{
	public static List list = new ArrayList();
}
class NdAdd{
	private String lock;

	public NdAdd(String lock) {
		super();
		this.lock = lock;
	}
	public void add(){
		synchronized (lock) {
			ValueObject.list.add("anyString");
			lock.notifyAll();
		}
	}
	
}
class NdSubtract{
	private String lock;

	public NdSubtract(String lock) {
		super();
		this.lock = lock;
	}
	public void subtract(){
		try {
			synchronized (lock) {
//				if (ValueObject.list.size() == 0) {//异常
				while (ValueObject.list.size() == 0) {//正常
					System.out.println("wait begin ThreadName="
							+ Thread.currentThread().getName());
					lock.wait();
					System.out.println("wait end ThreadName="
							+ Thread.currentThread().getName());
				}
				ValueObject.list.remove(0);
				System.out.println("list size=" + ValueObject.list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadAdd extends Thread{
	private NdAdd p;

	public ThreadAdd(NdAdd p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		p.add();
	}
}

class ThreadSubtract extends Thread{
	private NdSubtract r;

	public ThreadSubtract(NdSubtract r) {
		super();
		this.r = r;
	}

	@Override
	public void run() {
		r.subtract();
	}
	
}