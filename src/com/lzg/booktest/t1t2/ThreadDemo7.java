package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午4:17:06 
 */
public class ThreadDemo7 {
	/**
	 * “非线程安全”问题存在于“实例变量”中，如果是方法内部的私有变量，
	 * 则不存在“非线程安全”问题，所得结果也就是“线程安全”的了。
	 * @param args
	 * 2017年8月13日 下午4:26:00
	 */
	public static void main1(String[] args) {
		HasSelfPrivateNum numRef = new HasSelfPrivateNum();
		ThreadTest71 t1 = new ThreadTest71(numRef);
		t1.start();
		
		ThreadTest72 t2 = new ThreadTest72(numRef);
		t2.start();
	}
	/**
	 * 实例变量非线程安全
	 * 两个线程同时访问一个没有同步的方法，当两个线程同时
	 * 操作业务对象中的实例变量，则有可能会出现“非线程安全”问题。
	 * @param args
	 * 2017年8月13日 下午4:35:04
	 */
	public static void main2(String[] args) {
		HasPrivateNum numRef = new HasPrivateNum();
		
		ThreadTest73 t1 = new ThreadTest73(numRef);
		t1.start();
		
		ThreadTest74 t2 = new ThreadTest74(numRef);
		t2.start();
	}
	/**
	 * 多个对象多个锁
	 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码或
	 * 方法（函数）当作锁。
	 * @param args
	 * 2017年8月13日 下午5:08:25
	 */
	public static void main(String[] args) {
		HasPrivateNum numRef1 = new HasPrivateNum();
		HasPrivateNum numRef2 = new HasPrivateNum();
		
		ThreadTest75 t1 = new ThreadTest75(numRef1);
		t1.start();
		
		ThreadTest76 t2 = new ThreadTest76(numRef2);
		t2.start();
	}

}
class HasSelfPrivateNum{
	public void addI(String username){
		try {
			int num = 0;
			if (username.equals("a")) {
				num = 100;
				System.out.println("a set over!");
				Thread.sleep(2000);
			} else {
				num = 200;
				System.out.println("b set over!");
			}
			System.out.println(username + " num=" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest71 extends Thread{
	private HasSelfPrivateNum numRef;
	public ThreadTest71(HasSelfPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("a");
	}
}
class ThreadTest72 extends Thread{
	private HasSelfPrivateNum numRef;
	public ThreadTest72(HasSelfPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("b");
	}
}
class HasPrivateNum{
	private int num = 0;
//	public void addI(String username){//“非线程安全”问题
	public synchronized void addI(String username){//线程安全,在两个线程访问同一个对象中的同步方法时一定是线程安全的。
		try {
			if (username.equals("a")) {
				num = 100;
				System.out.println("a set over!");
				Thread.sleep(2000);
			} else {
				num = 200;
				System.out.println("b set over!");
			}
			System.out.println(username + " num=" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest73 extends Thread{
	private HasPrivateNum numRef;
	public ThreadTest73(HasPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("a");
	}
}
class ThreadTest74 extends Thread{
	private HasPrivateNum numRef;
	public ThreadTest74(HasPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("b");
	}
}
class ThreadTest75 extends Thread{
	private HasPrivateNum numRef;
	public ThreadTest75(HasPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("a");
	}
}
class ThreadTest76 extends Thread{
	private HasPrivateNum numRef;
	public ThreadTest76(HasPrivateNum numRef){
		this.numRef = numRef;
	}
	@Override
	public void run() {
		numRef.addI("b");
	}
}