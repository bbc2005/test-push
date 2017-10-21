package com.lzg.booktest.t3.s1;

import java.util.ArrayList;
import java.util.List;

/** 
 * 等待/通知机制
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月21日 下午10:01:03 
 */
public class WaitNotifyDemo1 {
	/**
	 * 不使用等待/通知机制实现线程间通信
	 * 弊端：线程b不停地通过while语句轮询机制来检测某一个条件，
	 * 这样会浪费CPU资源。
	 * 如果轮询的时间间隔很小，更浪费CPU资源；
	 * 如果轮询的时间间隔很大，有可能会收不到想要
	 * 得到的数据。
	 * @param args
	 * 2017年8月21日 下午10:01:30
	 */
	public static void main1(String[] args) {
		Wn1MyList service = new Wn1MyList();
		Wn1ThreadA a = new Wn1ThreadA(service);
		a.start();
		
		Wn1ThreadB b = new Wn1ThreadB(service);
		b.start();
	}
	
	/**
	 * 等待/通知机制的实现
	 * 方法wait()的作用是使当前执行代码的线程进行等待，wait()方法
	 * 是Object类的方法。该方法用来将当前线程置入“预执行队列”中，并且在wait()
	 * 所在的代码行处停止执行，直到接到通知或中断为止。在调用wait()之前，线程必须
	 * 获得该对象的对象级别锁，即只能在同步方法或同步块中调用wait()方法。在执行wait()
	 * 方法后，当前线程释放锁。在从wait()返回前，线程与其他线程竞争重新获得锁。如果调用
	 * wait()时没有持有适当的锁，则抛出IllegalMonitorStateException.
	 * 
	 * 方法notify()也要在同步方法或同步块中调用，即在调用前，线程也必须获得该对象的对象级别锁。
	 * 如果调用notify()时没有持有适当的锁，也会抛出IllegalMonitorStateException。
	 * 该方法用来通知那些可能等待该对象锁的其他线程，如果有多个线程等待，则由线程规划器随机挑选
	 * 出其中一个呈wait状态的线程，对其发成通知notify，并使它等待获取该对象的对象锁。需要注意的
	 * 是：在执行notify()方法后，当前线程不会马上释放该对象锁，呈wait状态的线程也并不能马上获取
	 * 该对象锁，要等到执行notify()方法的线程将程序执行完，也就是退出synchronized代码块后，当前
	 * 线程才会释放锁，而呈wait状态所在的线程才可以获取该对象锁。当第一个获得了该对象锁的wait线程运行
	 * 完毕以后，它会释放掉该对象锁，此时如果该对象没有再次使用notify语句，则即便该对象已经空闲，其他
	 * wait状态等待的线程由于没有得到该对象的通知，还会继续阻塞在wait状态，直到这个对象发出一个notify
	 * 或notifyAll.
	 * 
	 * wait使线程停止运行。
	 * notify使停止的线程继续运行。
	 * 
	 */
	
	/**
	 * 异常原因：没有“对象监视器”，也就是没有同步加锁。
	 * @param args
	 * 2017年8月21日 下午10:23:17
	 */
	public static void main2(String[] args) {
		try {
			String newString = new String("");
			newString.wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 永远在等待
	 * @param args
	 * 2017年8月21日 下午10:46:18
	 */
	public static void main3(String[] args) {
		try {
			String lock = new String();
			System.out.println("syn上面");
			synchronized (lock) {
				System.out.println("syn第一行");
				lock.wait();
				System.out.println("wait下的代码！");
			}
			System.out.println("syn下面的代码");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main4(String[] args) {
		try {
			Object lock = new Object();
			Wn1Thread1 t1 = new Wn1Thread1(lock);
			t1.start();
			Thread.sleep(3000);
			Wn1Thread2 t2 = new Wn1Thread2(lock);
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			WnThreadA1 a = new WnThreadA1(lock);
			a.start();
			Thread.sleep(50);
			WnThreadB1 b = new WnThreadB1(lock);
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class Wn1MyList{
	private List list = new ArrayList();
	public void add(){
		list.add("asd");
	}
	public int size(){
		return list.size();
	}
}
class Wn1ThreadA extends Thread{
	private Wn1MyList list;

	public Wn1ThreadA(Wn1MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				list.add();
				System.out.println("添加了" + (i + 1) + "个元素");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Wn1ThreadB extends Thread{
	private Wn1MyList list;

	public Wn1ThreadB(Wn1MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while(true){
				if(list.size() == 5){
					System.out.println("==5了，线程b要退出了！");
					throw new InterruptedException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Wn1Thread1 extends Thread{
	private Object lock;

	public Wn1Thread1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println("开始                 wait  time ="
						+ System.currentTimeMillis());
				lock.wait();
				System.out.println("结束                  wait  time ="
						+ System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Wn1Thread2 extends Thread{
	private Object lock;

	public Wn1Thread2(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {   
		synchronized(lock){
			System.out.println("开始notify time=" + System.currentTimeMillis());
			lock.notify();
			System.out.println("结束notify time=" + System.currentTimeMillis());
		}
	}
	
}
class WnMyList1{
	private static List list = new ArrayList();
	public static void add(){
		list.add("anyString");
	}
	public static int size(){
		return list.size();
	}
}
class WnThreadA1 extends Thread{
	private Object lock;

	public WnThreadA1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				if (WnMyList1.size() != 5) {
					System.out.println("wait begin "
							+ System.currentTimeMillis());
					lock.wait();
					System.out
							.println("wait end " + System.currentTimeMillis());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class WnThreadB1 extends Thread{
	private Object lock;

	public WnThreadB1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					WnMyList1.add();
					if (WnMyList1.size() == 5) {
						lock.notify();
						System.out.println("已发出通知！");
					}
					System.out.println("添加了" + (i + 1) + "个元素！");
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}