package com.lzg.booktest.t1t2;
/** 
 * ��JVM�о���String�����ػ���Ĺ��ܣ�����
 * ��synchronized(string)ͬ������String����ʹ��ʱ��Ҫע��
 * �����ش�����һЩ���⡣
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��16�� ����10:07:54 
 */
public class StringThread {
	/**
	 * �����߳�a��b������ͬ���������b����ִ�С�
	 * ����ڴ����������£�ͬ��synchronized����鶼��ʹ��String��Ϊ�����󣬶�
	 * ������������new Object()ʵ����һ��Object���󣬵�
	 * ���������˻����С�
	 * @param args
	 * 2017��8��16�� ����10:17:05
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
	 * �����ӡ��ԭ���ǳ��е�������ͬһ����
	 * @param args
	 * 2017��8��16�� ����10:23:47
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