package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午5:16:39 
 */
public class ThreadDemo8 {
	/**
	 * synchronized方法与锁对象
	 * @param args
	 * 2017年8月13日 下午5:43:40
	 */
	public static void main1(String[] args) {
		ThreadTest8 t = new ThreadTest8();
		ThreadTest81 a = new ThreadTest81(t);
		a.setName("a");
		ThreadTest82 b = new ThreadTest82(t);
		b.setName("b");
		
		a.start();
		b.start();
	}
	/**
	 * 1：A线程先持有object对象的Lock锁，B线程可以以异步的方式调用
	 * object对象中的非synchronized类型的方法；
	 * 2：A线程先持有object对象的Lock锁，B线程如果在这时调用object
	 * 对象中的synchronized类型的方法则需要等待，也就是同步。
	 * @param args
	 * 2017年8月13日 下午5:38:41
	 */
	public static void main(String[] args) {
		ThreadTest83 object = new ThreadTest83();
		ThreadTest84 a = new ThreadTest84(object);
		a.setName("A");
		ThreadTest85 b = new ThreadTest85(object);
		b.setName("B");
		a.start();
		b.start();
	}

}
class ThreadTest8{
//	public void methodA(){
	public synchronized void methodA(){//调用关键字synchronized声明的方法一定是排队运行的
		try {
			System.out.println("begin methodA threadName="
					+ Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest81 extends Thread{
	private ThreadTest8 t;
	public ThreadTest81(ThreadTest8 t){
		this.t = t;
	}
	@Override
	public void run() {
		t.methodA();
	}
	
}
class ThreadTest82 extends Thread{
	private ThreadTest8 t;
	public ThreadTest82(ThreadTest8 t){
		this.t = t;
	}
	@Override
	public void run() {
		t.methodA();
	}
	
}
class ThreadTest83{
	public synchronized void methodA(){//调用关键字synchronized声明的方法一定是排队运行的
		try {
			System.out.println("begin methodA threadName="
					+ Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end endTime=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//public void methodB(){//未同步
	public synchronized void methodB(){//同步
		try {
			System.out.println("begin methodB threadName="
					+ Thread.currentThread().getName() + " begin time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest84 extends Thread{
	private ThreadTest83 object;
	public ThreadTest84(ThreadTest83 object){
		this.object = object;
	}
	@Override
	public void run() {
		object.methodA();
	}
}
class ThreadTest85 extends Thread{
	private ThreadTest83 object;
	public ThreadTest85(ThreadTest83 object){
		this.object = object;
	}
	@Override
	public void run() {
		object.methodB();
	}
}