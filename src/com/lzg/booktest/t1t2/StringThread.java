package com.lzg.booktest.t1t2;
/** 
 * 在JVM中具有String常量池缓存的功能，所以
 * 将synchronized(string)同步块与String联合使用时，要注意
 * 常量池带来的一些例外。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月16日 下午10:07:54 
 */
public class StringThread {
	/**
	 * 两个线程a和b持有相同的锁，造成b不能执行。
	 * 因此在大多数的情况下，同步synchronized代码块都不使用String作为锁对象，而
	 * 改用其他，如new Object()实例化一个Object对象，但
	 * 它并不放人缓存中。
	 * @param args
	 * 2017年8月16日 下午10:17:05
	 */
	public static void main1(String[] args) {
		StringThreadService service = new StringThreadService();
		StringThreadA a = new StringThreadA(service);
		a.setName("A");
		a.start();
		
		StringThreadB b = new StringThreadB(service);
		b.setName("B");
		b.start();
	}
	/**
	 * 交替打印的原因：是持有的锁不是同一个。
	 * @param args
	 * 2017年8月16日 下午10:23:47
	 */
	public static void main(String[] args) {
		StringThreadService1 service = new StringThreadService1();
		StringThreadA1 a = new StringThreadA1(service);
		a.setName("A");
		a.start();
		
		StringThreadB1 b = new StringThreadB1(service);
		b.setName("B");
		b.start();
	}

}
class StringThreadService{
	public static void print(String string){
		try {
			synchronized (string) {
				while (true) {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class StringThreadA extends Thread{
	private StringThreadService service;
	public StringThreadA(StringThreadService service){
		this.service = service;
	}
	@Override
	public void run() {
		service.print("AA");
	}
}

class StringThreadB extends Thread{
	private StringThreadService service;
	public StringThreadB(StringThreadService service){
		this.service = service;
	}
	@Override
	public void run() {
		service.print("AA");
	}
}

class StringThreadService1{
	public void print(Object object){
		try {
			synchronized (object) {
				while (true) {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class StringThreadA1 extends Thread{
	private StringThreadService1 service;
	public StringThreadA1(StringThreadService1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.print(new Object());
	}
}

class StringThreadB1 extends Thread{
	private StringThreadService1 service;
	public StringThreadB1(StringThreadService1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.print(new Object());
	}
}