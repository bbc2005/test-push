package com.lzg.booktest.t1t2;
/** 
 * һ���첽��һ��ͬ��
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��14�� ����9:54:55 
 */
public class ThreadDemo13 {
	/**
	 * ����synchronized���о����첽ִ�У���synchronized���о���ͬ��ִ�С�
	 * @param args
	 * 2017��8��14�� ����10:06:47
	 */
	public static void main1(String[] args) {
		ThreadDemo13Task task = new ThreadDemo13Task();
		
		ThreadTest13A a = new ThreadTest13A(task);
		a.start();
		
		ThreadTest13B b = new ThreadTest13B(task);
		b.start();
	}
	/**
	 * synchronized�������ͬ����
	 * ��Ϊsynchronizedʹ�õġ��������������һ����
	 * ������ʹ��ͬ��synchronized(this)�����ʱ��Ҫע�⣺
	 * ��һ���̷߳���object��һ��synchronized(this)ͬ��
	 * �����ʱ�������̶߳����obejcct����������synchronized(this)
	 * ͬ�������ķ��ʽ���������
	 * @param args
	 * 2017��8��14�� ����10:16:12
	 */
	public static void main2(String[] args) {
		ThreadDemo13Service service = new ThreadDemo13Service();
		ThreadTest131A a = new ThreadTest131A(service);
		a.setName("a");
		a.start();
		
		ThreadTest131B b = new ThreadTest131B(service);
		b.setName("b");
		b.start();
	}
	/**
	 * ��֤ͬ��synchronized(this)�������������ǰ�����
	 * @param args
	 * @throws InterruptedException
	 * 2017��8��14�� ����10:31:30
	 */
	public static void main(String[] args) throws InterruptedException {
		ThreadDemo131Task task = new ThreadDemo131Task();
		ThreadTest132A a = new ThreadTest132A(task);
		a.start();
		
		Thread.sleep(100);
		
		ThreadTest132B b = new ThreadTest132B(task);
		b.start();
	}

}
class ThreadDemo13Task{
	public void doLongTimeTask(){
		for (int i = 0; i < 100; i++) {
			System.out.println("nosynchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
		}
		System.out.println("");
		synchronized (this){
			for (int i = 0; i < 100; i++) {
				System.out.println("synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
			}
		}
	}
}

class ThreadTest13A extends Thread{
	private ThreadDemo13Task task;
	public ThreadTest13A(ThreadDemo13Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		task.doLongTimeTask();
	}
}

class ThreadTest13B extends Thread{
	private ThreadDemo13Task task;
	public ThreadTest13B(ThreadDemo13Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		task.doLongTimeTask();
	}
}
class ThreadDemo13Service{
	public void serviceMethodA(){
		synchronized(this){
			try {
				System.out
						.println("A begin time=" + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("A end time=" + System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void serviceMethodB(){
		synchronized(this){
			System.out.println("B begin time=" + System.currentTimeMillis());
			System.out.println("B end time=" + System.currentTimeMillis());
		}
	}
}
class ThreadTest131A extends Thread{
	private ThreadDemo13Service service;
	public ThreadTest131A(ThreadDemo13Service service){
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.serviceMethodA();
	}
}
class ThreadTest131B extends Thread{
	private ThreadDemo13Service service;
	public ThreadTest131B(ThreadDemo13Service service){
		this.service = service;
	}
	@Override
	public void run() {
		super.run();
		service.serviceMethodB();
	}
}
class ThreadDemo131Task{
//	public void otherMethod(){//�첽��ӡ
	public synchronized void otherMethod(){//ͬ����ӡ
		System.out.println("------------------run--otherMethod");
	}
	public void doLongTimeTask(){
		synchronized(this){
			for (int i = 0; i < 10000; i++) {
				System.out.println("synchronized threadName=" + Thread.currentThread().getName() + "i=" + (i + 1));
			}
		}
	}
}
class ThreadTest132A extends Thread{
	private ThreadDemo131Task task;
	public ThreadTest132A(ThreadDemo131Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		task.doLongTimeTask();
	}
}

class ThreadTest132B extends Thread{
	private ThreadDemo131Task task;
	public ThreadTest132B(ThreadDemo131Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		task.otherMethod();
	}
}