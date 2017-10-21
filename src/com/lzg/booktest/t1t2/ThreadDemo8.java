package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����5:16:39 
 */
public class ThreadDemo8 {
	/**
	 * synchronized������������
	 * @param args
	 * 2017��8��13�� ����5:43:40
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
	 * 1��A�߳��ȳ���object�����Lock����B�߳̿������첽�ķ�ʽ����
	 * object�����еķ�synchronized���͵ķ�����
	 * 2��A�߳��ȳ���object�����Lock����B�߳��������ʱ����object
	 * �����е�synchronized���͵ķ�������Ҫ�ȴ���Ҳ����ͬ����
	 * @param args
	 * 2017��8��13�� ����5:38:41
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
	public synchronized void methodA(){//���ùؼ���synchronized�����ķ���һ�����Ŷ����е�
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
	public synchronized void methodA(){//���ùؼ���synchronized�����ķ���һ�����Ŷ����е�
		try {
			System.out.println("begin methodA threadName="
					+ Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end endTime=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//public void methodB(){//δͬ��
	public synchronized void methodB(){//ͬ��
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