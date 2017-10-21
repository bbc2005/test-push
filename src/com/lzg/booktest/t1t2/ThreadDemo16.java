package com.lzg.booktest.t1t2;
/** 
 * 细化验证3个结论
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月15日 下午10:07:30 
 */
public class ThreadDemo16 {
	/**
	 * 第一个结论：
	 * 当多个线程同时执行synchronized(x){}同步代码块时呈同步效果。
	 * @param args
	 * 2017年8月15日 下午10:20:10
	 */
	public static void main1(String[] args) {
		ThreadDemo16Service1 service = new ThreadDemo16Service1();
		ThreadDemo16Object object = new ThreadDemo16Object();
		ThreadDemo16Object object1 = new ThreadDemo16Object();
		
		ThreadTest16A1 a = new ThreadTest16A1(service, object);
		a.setName("A");
		a.start();
		
//		ThreadTest16B b = new ThreadTest16B(service, object);//同一个对象监视器，结果同步
		ThreadTest16B1 b = new ThreadTest16B1(service, object1);//不是同一个对象监视器，结果异步
		b.setName("B");
		b.start();
	}
	/**
	 * 第二个结论：
	 * 当其他线程执行x对象中synchronized同步方法时呈同步效果
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月15日 下午10:37:43
	 */
	public static void main2(String[] args) throws InterruptedException {
		ThreadDemo16Service2 service = new ThreadDemo16Service2();
		Td16Object object = new Td16Object();
		
		ThreadTest16A2 a = new ThreadTest16A2(service, object);
		a.setName("A");
		a.start();
		
		Thread.sleep(100);
		
		ThreadTest16B2 b = new ThreadTest16B2(service, object);
		b.setName("B");
		b.start();
	}
	/**
	 * 第三个结论：
	 * 当其他线程执行x对象方法里面的synchronized(this)代码块时也呈现同步效果。
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月15日 下午10:42:42
	 */
	public static void main(String[] args) throws InterruptedException {
		ThreadDemo16Service3 service = new ThreadDemo16Service3();
		Td16Object3 object = new Td16Object3();
		
		ThreadTest16A3 a = new ThreadTest16A3(service, object);
		a.setName("A");
		a.start();
		
		Thread.sleep(100);
		
		ThreadTest16B3 b = new ThreadTest16B3(service, object);
		b.setName("B");
		b.start();
	}

}
class ThreadDemo16Object{
	
}
class ThreadDemo16Service1{
	public void test(ThreadDemo16Object object){
		synchronized(object){
			try {
				System.out.println("test ___ getLock  time="
						+ System.currentTimeMillis() + "run ThreadName="
						+ Thread.currentThread().getName());
				Thread.sleep(2000);
				System.out.println("test releaseLock time="
						+ System.currentTimeMillis() + " run  ThreadName="
						+ Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
class ThreadTest16A1 extends Thread{
	private ThreadDemo16Service1 service;
	private ThreadDemo16Object object;
	public ThreadTest16A1(ThreadDemo16Service1 service, ThreadDemo16Object object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.test(object);
	}
}
class ThreadTest16B1 extends Thread{
	private ThreadDemo16Service1 service;
	private ThreadDemo16Object object;
	public ThreadTest16B1(ThreadDemo16Service1 service, ThreadDemo16Object object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.test(object);
	}
}

class Td16Object{
	public synchronized void speedPrintString(){
		System.out.println("speedPrintString ___getLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
		System.out.println("------------------");
		System.out.println("speedPrintString releaseLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
	}
}
class ThreadDemo16Service2{
	public void test(Td16Object object){
		synchronized (object) {
			try {
				System.out.println("test ___getLock time="
						+ System.currentTimeMillis() + "run ThreadName="
						+ Thread.currentThread().getName());
				Thread.sleep(5000);
				System.out.println("test releaseLock time="
						+ System.currentTimeMillis() + "run ThreadName="
						+ Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadTest16A2 extends Thread{
	private ThreadDemo16Service2 service;
	private Td16Object object;
	public ThreadTest16A2(ThreadDemo16Service2 service, Td16Object object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.test(object);
	}
}
class ThreadTest16B2 extends Thread{
	private ThreadDemo16Service2 service;
	private Td16Object object;
	public ThreadTest16B2(ThreadDemo16Service2 service, Td16Object object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		object.speedPrintString();
	}
}

class Td16Object3{
	public void speedPrintString(){
		synchronized (this) {
			System.out.println("speedPrintString ___getLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
			System.out.println("------------------");
			System.out.println("speedPrintString releaseLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
		}
	}
}
class ThreadDemo16Service3{
	public void test(Td16Object3 object){
		synchronized (object) {
			try {
				System.out.println("test ___getLock time="
						+ System.currentTimeMillis() + "run ThreadName="
						+ Thread.currentThread().getName());
				Thread.sleep(5000);
				System.out.println("test releaseLock time="
						+ System.currentTimeMillis() + "run ThreadName="
						+ Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadTest16A3 extends Thread{
	private ThreadDemo16Service3 service;
	private Td16Object3 object;
	public ThreadTest16A3(ThreadDemo16Service3 service, Td16Object3 object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		service.test(object);
	}
}
class ThreadTest16B3 extends Thread{
	private ThreadDemo16Service3 service;
	private Td16Object3 object;
	public ThreadTest16B3(ThreadDemo16Service3 service, Td16Object3 object) {
		super();
		this.service = service;
		this.object = object;
	}
	@Override
	public void run() {
		super.run();
		object.speedPrintString();
	}
}