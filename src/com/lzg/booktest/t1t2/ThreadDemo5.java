package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����10:40:11 
 * ��ͣ�̣߳�
 * ʹ��suspend()������ͣ�̣߳�ʹ��resume()�����ָ��̵߳�ִ�С�
 * 
 */
public class ThreadDemo5 {

	public static void main1(String[] args) {
		try {
			ThreadTest5 t = new ThreadTest5();
			t.start();
			Thread.sleep(5000);
			//A��
			t.suspend();
			System.out.println("A=" + System.currentTimeMillis() + " i="
					+ t.getI());
			Thread.sleep(5000);
			System.out.println("A=" + System.currentTimeMillis() + " i="
					+ t.getI());
			//B��
			t.resume();
			Thread.sleep(5000);
			//C��
			t.suspend();
			System.out.println("B=" + System.currentTimeMillis() + " i="
					+ t.getI());
			Thread.sleep(5000);
			System.out.println("B=" + System.currentTimeMillis() + " i="
					+ t.getI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//suspend��resume������ȱ�㣺��ռ
	public static void main2(String[] args) {
		try {
			final ThreadTest51 t = new ThreadTest51();
			Thread t1 = new Thread() {
				@Override
				public void run() {
					t.printString();
				}
			};
			t1.setName("a");
			t1.start();
			Thread.sleep(1000);
			Thread t2 = new Thread() {
				@Override
				public void run() {
					System.out.println("t2�����ˣ������벻��printString()������ֻ��ӡ1��begin");
					System.out
							.println("��ΪprintString()������a�߳�����������Զsuspend��ͣ�ˣ�");
					t.printString();
				}
			};
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main3(String[] args) {
		try {
			ThreadTest52 t = new ThreadTest52();
			t.start();
			Thread.sleep(1000);
			t.suspend();
			System.out.println("main end!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final ThreadTest53 t = new ThreadTest53();
		
		Thread t1 = new Thread(){
			@Override
			public void run() {
				t.setValue("a", "aa");
			}
		};
		t1.setName("a");
		t1.start();
		
		Thread.sleep(500);
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
				t.print();
			}
		};
		t2.start();
		
	}
}
class ThreadTest5 extends Thread{
	private long i = 0;
	public long getI(){
		return i;
	}
	public void setI(long i){
		this.i = i;
	}
	@Override
	public void run() {
		while(true){
			i++;
		}
	}
	
}
/**
 * ʹ��suspend��resume����ʱ�����ʹ�ò�����������ɹ���
 * ��ͬ������Ķ�ռ��ʹ�������߳��޷����ʹ���ͬ������
 * @author DELL
 *
 */
class ThreadTest51 extends Thread{
	synchronized public void printString(){
		System.out.println("begin");
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("a�߳���Զsuspend�ˣ�");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}
/**
 * ���������е�print()�����ڲ�ֹͣʱ��ͬ����δ���ͷš�
 * ���µ�ǰPrintStream�����println()����һֱ�ʡ���ͣ��״̬��
 * ���ҡ���δ�ͷš�����main()�����еĴ���System.out.println("main end!")���ٳٲ���ִ�д�ӡ��
 *
 */
class ThreadTest52 extends Thread{
	private long i = 0;
	@Override
	public void run() {
		while(true){
			i++;
			System.out.println(i);//����̨������ӡmain end
		}
	}
}
/**
 * suspend �� resume ������ȱ�㣺��ͬ��
 *
 */
class ThreadTest53 extends Thread{
	private String username = "1";
	private String password = "11";
	public void setValue(String u,String p){
		this.username = u;
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("ֹͣa�̣߳�");
			Thread.currentThread().suspend();
		}
		this.password = p;
	}
	public void print(){
		System.out.println(username + " " +password);
	}
}