package com.lzg.booktest.t1t2;
/**
 * synchronized关键字修饰static静态方法是给Class类上锁，
 * 而synchronized修饰非static静态方法是给对象上锁。 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月16日 下午9:13:21 
 */
public class ThreadDemo17 {
	/**
	 * synchronized修饰static静态方法上，
	 * 是对当前的*.java文件对应的Class类进行持锁。
	 * @param args
	 * 2017年8月16日 下午9:30:32
	 */
	public static void main1(String[] args) {
		Td17A a = new Td17A();
		a.setName("A");
		a.start();
		
		Td17B b = new Td17B();
		b.setName("B");
		b.start();
	}
	/**
	 * 异步原因：持有不同的锁，一个是对象性锁，另外一个是Class锁，
	 * @param args
	 * 2017年8月16日 下午9:46:36
	 */
	public static void main2(String[] args) {
		Td17Service1 service = new Td17Service1();
		Td17A1 a = new Td17A1(service);
		a.setName("A");
		a.start();
		
		Td17B1 b = new Td17B1(service);
		b.setName("B");
		b.start();
		
		Td17C1 c = new Td17C1(service);
		c.setName("C");
		c.start();
	}
	/**
	 * Class锁可以对类的所有对象实例起作用。
	 * @param args
	 * 2017年8月16日 下午9:49:52
	 */
	public static void main3(String[] args) {
		Td17Service1 service1 = new Td17Service1();
		Td17Service1 service2 = new Td17Service1();
		Td17A1 a = new Td17A1(service1);
		a.setName("A");
		a.start();
		
		Td17B1 b = new Td17B1(service2);
		b.setName("B");
		b.start();
	}
	/**
	 * synchronized(class)代码块的作用和synchronized static方法的作用一样。
	 * @param args
	 * 2017年8月16日 下午9:57:26
	 */
	public static void main(String[] args) {
		Td17Service2 service1 = new Td17Service2();
		Td17Service2 service2 = new Td17Service2();
		
		Td17A2 a = new Td17A2(service1);
		a.setName("A");
		a.start();
		
		Td17B2 b = new Td17B2(service2);
		b.setName("B");
		b.start();
		
	}

}
class Td17Service{
	public static synchronized void printA(){
		try {
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "进入printA");
			Thread.sleep(3000);
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "离开printA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void printB(){
		try {
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "进入printB");
			Thread.sleep(3000);
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "离开printB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Td17A extends Thread{

	@Override
	public void run() {
		Td17Service.printA();
	}
	
}
class Td17B extends Thread{

	@Override
	public void run() {
		Td17Service.printB();
	}
	
}

class Td17Service1{
	public static synchronized void printA(){//Class锁，可以对类的所有对象实例起作用。
		try {
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "进入printA");
			Thread.sleep(5000);
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "离开printA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void printB(){//Class锁
		try {
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "进入printB");
			Thread.sleep(5000);
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "离开printB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void printC(){//对象锁
		try {
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "进入printC");
			Thread.sleep(5000);
			System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
					+ System.currentTimeMillis() + "离开printC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Td17A1 extends Thread{
	private Td17Service1 service;
	public Td17A1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printA();
	}
	
}
class Td17B1 extends Thread{
	private Td17Service1 service;
	public Td17B1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printB();
	}
	
}
class Td17C1 extends Thread{
	private Td17Service1 service;
	public Td17C1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printC();
	}
}

class Td17Service2{
	public static void printA(){
		synchronized(Td17Service2.class){
			try {
				System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
						+ System.currentTimeMillis() + "进入printA");
				Thread.sleep(5000);
				System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
						+ System.currentTimeMillis() + "离开printA");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void printB(){
		synchronized(Td17Service2.class){
			try {
				System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
						+ System.currentTimeMillis() + "进入printB");
				Thread.sleep(5000);
				System.out.println("线程名称=" + Thread.currentThread().getName() + "在"
						+ System.currentTimeMillis() + "离开printB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Td17A2 extends Thread{
	private Td17Service2 service;
	public Td17A2(Td17Service2 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printA();
	}
	
}
class Td17B2 extends Thread{
	private Td17Service2 service;
	public Td17B2(Td17Service2 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printB();
	}
	
}
