package com.lzg.booktest.t1t2;
/** 
 * �����������Ϊ���������
 * ����̵߳���ͬһ�������еĲ�ͬ���Ƶ�synchronizedͬ��������
 * synchronized(this)ͬ�������ʱ�����õ�Ч�����ǰ�˳��ִ�У�Ҳ����
 * ͬ���ģ������ġ�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��14�� ����10:40:47 
 */
public class ThreadDemo14 {
	/**
	 * ��������󡱴������ʵ�������������Ĳ�����
	 * ʹ�ø�ʽΪ��synchronized(��this����x)
	 * @param args
	 * 2017��8��14�� ����10:55:18
	 */
	public static void main(String[] args) {
		ThreadDemo14Service service = new ThreadDemo14Service();
		
		ThreadTest14A a = new ThreadTest14A(service);
		a.setName("A");
		a.start();
		
		ThreadTest14B b = new ThreadTest14B(service);
		b.setName("B");
		b.start();
	}

}
class ThreadDemo14Service{
	private String username;
	private String password;
	private String anyString = new String();
	public void set(String username,String password){
		try {
			synchronized (anyString) {
				System.out.println("�߳�����Ϊ��" + Thread.currentThread().getName()
						+ "��" + System.currentTimeMillis() + "����ͬ����");
				this.username = username;
				Thread.sleep(3000);
				this.password = password;
				System.out.println("�߳�����Ϊ��" + Thread.currentThread().getName()
						+ "��" + System.currentTimeMillis() + "�뿪ͬ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest14A extends Thread{
	private ThreadDemo14Service service;
	public ThreadTest14A(ThreadDemo14Service service){
		this.service = service;
	}
	@Override
	public void run() {
		service.set("a", "aa");
	}
}
class ThreadTest14B extends Thread{
	private ThreadDemo14Service service;
	public ThreadTest14B(ThreadDemo14Service service){
		this.service = service;
	}
	@Override
	public void run() {
		service.set("b", "bb");
	}
}