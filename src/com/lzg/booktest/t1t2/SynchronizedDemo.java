package com.lzg.booktest.t1t2;
/** 
 * synchronized������
 * �ؼ���synchronizedӵ��������Ĺ��ܣ�Ҳ������ʹ��
 * synchronizedʱ����һ���̵߳õ�һ�����������ٴ�����˶���
 * ��ʱ�ǿ����ٴεõ��ö�������ġ�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����9:00:10 
 * 
 * ���������������Լ������ٴλ�ȡ�Լ����ڲ�����
 */
public class SynchronizedDemo {
	/**
	 * ��һ��synchronized����/����ڲ�����
	 * ���������synchronized����/��ʱ������Զ���Եõ����ġ�
	 * @param args
	 * 2017��8��13�� ����9:07:13
	 */
	public static void main1(String[] args) {
		SynchronizedTest t = new SynchronizedTest();
		t.start();
	}
	/**
	 * �����ڸ�����̳й�ϵʱ����������ȫ����ͨ�����������������ø����ͬ�������ġ�
	 * @param args
	 * 2017��8��13�� ����9:18:04
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