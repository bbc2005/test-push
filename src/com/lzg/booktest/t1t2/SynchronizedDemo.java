package com.lzg.booktest.t1t2;
/** 
 * synchronized锁重入
 * 关键字synchronized拥有锁重入的功能，也就是在使用
 * synchronized时，当一个线程得到一个对象锁后，再次请求此对象
 * 锁时是可以再次得到该对象的锁的。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午9:00:10 
 * 
 * “可重入锁”：自己可以再次获取自己的内部锁。
 */
public class SynchronizedDemo {
	/**
	 * 在一个synchronized方法/块的内部调用
	 * 本类的其他synchronized方法/块时，是永远可以得到锁的。
	 * @param args
	 * 2017年8月13日 下午9:07:13
	 */
	public static void main1(String[] args) {
		SynchronizedTest t = new SynchronizedTest();
		t.start();
	}
	/**
	 * 当存在父子类继承关系时，子类是完全可以通过“可重入锁”调用父类的同步方法的。
	 * @param args
	 * 2017年8月13日 下午9:18:04
	 */
	public static void main(String[] args) {
		SynchronizedTest1 t = new SynchronizedTest1();
		t.start();
	}
}
class SynchronizedDemoService{
	public synchronized void service1(){
		System.out.println("service1");
		service2();
	}
	public synchronized void service2(){
		System.out.println("service2");
		service3();
	}
	public synchronized void service3(){
		System.out.println("service3");
	}
}
class SynchronizedTest extends Thread{

	@Override
	public void run() {
		SynchronizedDemoService service = new SynchronizedDemoService();
		service.service1();
	}
	
}
class Main{
	public int i = 10;
	public synchronized void operateIMainMethod(){
		try {
			i--;
			System.out.println("main print i=" + i);
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Sub extends Main{
	public synchronized void operateISubMethod(){
		try {
			while (i > 0) {
				i--;
				System.out.println("sub print i=" + i);
				Thread.sleep(100);
				this.operateIMainMethod();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class SynchronizedTest1 extends Thread{

	@Override
	public void run() {
		Sub sub = new Sub();
		sub.operateISubMethod();
	}
	
}