package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����4:17:06 
 */
public class ThreadDemo7 {
	/**
	 * �����̰߳�ȫ����������ڡ�ʵ���������У�����Ƿ����ڲ���˽�б�����
	 * �򲻴��ڡ����̰߳�ȫ�����⣬���ý��Ҳ���ǡ��̰߳�ȫ�����ˡ�
	 * @param args
	 * 2017��8��13�� ����4:26:00
	 */
	public static void main1(String[] args) {
		HasSelfPrivateNum numRef = new HasSelfPrivateNum();
		ThreadTest71 t1 = new ThreadTest71(numRef);
		t1.start();
		
		ThreadTest72 t2 = new ThreadTest72(numRef);
		t2.start();
	}
	/**
	 * ʵ���������̰߳�ȫ
	 * �����߳�ͬʱ����һ��û��ͬ���ķ������������߳�ͬʱ
	 * ����ҵ������е�ʵ�����������п��ܻ���֡����̰߳�ȫ�����⡣
	 * @param args
	 * 2017��8��13�� ����4:35:04
	 */
	public static void main2(String[] args) {
		HasPrivateNum numRef = new HasPrivateNum();
		
		ThreadTest73 t1 = new ThreadTest73(numRef);
		t1.start();
		
		ThreadTest74 t2 = new ThreadTest74(numRef);
		t2.start();
	}
	/**
	 * �����������
	 * �ؼ���synchronizedȡ�õ������Ƕ������������ǰ�һ�δ����
	 * ��������������������
	 * @param args
	 * 2017��8��13�� ����5:08:25
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
//	public void addI(String username){//�����̰߳�ȫ������
	public synchronized void addI(String username){//�̰߳�ȫ,�������̷߳���ͬһ�������е�ͬ������ʱһ�����̰߳�ȫ�ġ�
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