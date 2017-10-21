package com.lzg.booktest.t3.s1;

import java.util.ArrayList;
import java.util.List;

/** 
 * ������/������ģʽʵ��
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��24�� ����9:46:57 
 */
public class ConsumerProductDemo {

	/**
	 * һ������һ���ѣ�����ֵ
	 * @param args
	 * 2017��8��24�� ����10:01:41
	 */
	public static void main1(String[] args) {
		String lock = new String("");
		P1 p = new P1(lock);
		C1 c = new C1(lock);
		
		CpdThreadP1 pThread = new CpdThreadP1(p);
		CpdThreadC1 cThread = new CpdThreadC1(c);
		
		pThread.start();
		cThread.start();
	}
	/**
	 * ������������ѣ�����ֵ--����
	 * ����������������ʵ�����߳̽���waiting�ȴ�״̬��
	 * ���ȫ���̶߳�����waiting״̬�������Ͳ���ִ���κ�
	 * ҵ�����ˣ�������Ŀ��ֹͣ״̬��
	 * �������ֵ���Ҫԭ�����п�����������ͬ�ࡣ
	 * ����취�����⻽��ͬ�࣬������Ҳһͬ���Ѿͽ���ˡ�
	 * @param args
	 * 2017��8��24�� ����10:03:37
	 * @throws InterruptedException 
	 */
	public static void main2(String[] args) throws InterruptedException {
		String lock = new String("");
		P2 p = new P2(lock);
		C2 c = new C2(lock);
		
		CpdThreadP2[] pThreads = new CpdThreadP2[2];
		CpdThreadC2[] cThreads = new CpdThreadC2[2];
		for (int i = 0; i < cThreads.length; i++) {
			pThreads[i] = new CpdThreadP2(p);
			pThreads[i].setName("������" + (i + 1));
			cThreads[i] = new CpdThreadC2(c);
			cThreads[i].setName("������" + (i + 1));
			pThreads[i].start();
			cThreads[i].start();
			
		}
		
		Thread.sleep(5000);
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);
		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + "  " + threadArray[i].getState());
		}
	}
	/**
	 * һ������һ���ѣ�����ջ
	 * ʹ���������ջlist�����з������ݣ�ʹ������
	 * ��list��ջ��ȡ�����ݡ�list���������1��ֻ��һ����������һ�������ߡ�
	 * @param args
	 * 2017��8��24�� ����11:02:31
	 */
	public static void main3(String[] args) {
		MyStack1 myStack = new MyStack1();
		P3 p = new P3(myStack);
		C3 c = new C3(myStack);
		CpdThreadP3 pt = new CpdThreadP3(p);
		CpdThreadC3 ct = new CpdThreadC3(c);
		pt.start();
		ct.start();
	}
	/**
	 * һ�����������--����ջ�����wait�����ı������
	 * @param args
	 * 2017��8��24�� ����11:28:00
	 */
	public static void main4(String[] args) {
		MyStack2 myStack = new MyStack2();
		P4 p = new P4(myStack);
		C4 c1 = new C4(myStack);
		C4 c2 = new C4(myStack);
		C4 c3 = new C4(myStack);
		C4 c4 = new C4(myStack);
		C4 c5 = new C4(myStack);
		
		CpdThreadP4 pt = new CpdThreadP4(p);
		pt.start();
		
		CpdThreadC4 ct1 = new CpdThreadC4(c1);
		CpdThreadC4 ct2 = new CpdThreadC4(c2);
		CpdThreadC4 ct3 = new CpdThreadC4(c3);
		CpdThreadC4 ct4 = new CpdThreadC4(c4);
		CpdThreadC4 ct5 = new CpdThreadC4(c5);
		
		ct1.start();
		ct2.start();
		ct3.start();
		ct4.start();
		ct5.start();
	}
	/**
	 * ��������һ���ѣ�����ջ
	 * @param args
	 * 2017��8��24�� ����11:45:51
	 */
	public static void main5(String[] args) {
		MyStack2 myStack = new MyStack2();
		P4 p1 = new P4(myStack);
		P4 p2 = new P4(myStack);
		P4 p3 = new P4(myStack);
		P4 p4 = new P4(myStack);
		P4 p5 = new P4(myStack);
		P4 p6 = new P4(myStack);
		
		CpdThreadP4 pt1 = new CpdThreadP4(p1);
		CpdThreadP4 pt2 = new CpdThreadP4(p2);
		CpdThreadP4 pt3 = new CpdThreadP4(p3);
		CpdThreadP4 pt4 = new CpdThreadP4(p4);
		CpdThreadP4 pt5 = new CpdThreadP4(p5);
		CpdThreadP4 pt6 = new CpdThreadP4(p6);
		pt1.start();
		pt2.start();
		pt3.start();
		pt4.start();
		pt5.start();
		pt6.start();
		
		C4 c1 = new C4(myStack);
		CpdThreadC4 ct = new CpdThreadC4(c1);
		ct.start();
	}
	/**
	 * ����������������ߣ�����ջ
	 * @param args
	 * 2017��8��24�� ����11:57:24
	 */
	public static void main(String[] args) {
		MyStack2 myStack = new MyStack2();
		P4 p1 = new P4(myStack);
		P4 p2 = new P4(myStack);
		P4 p3 = new P4(myStack);
		P4 p4 = new P4(myStack);
		P4 p5 = new P4(myStack);
		P4 p6 = new P4(myStack);
		
		CpdThreadP4 pt1 = new CpdThreadP4(p1);
		CpdThreadP4 pt2 = new CpdThreadP4(p2);
		CpdThreadP4 pt3 = new CpdThreadP4(p3);
		CpdThreadP4 pt4 = new CpdThreadP4(p4);
		CpdThreadP4 pt5 = new CpdThreadP4(p5);
		CpdThreadP4 pt6 = new CpdThreadP4(p6);
		pt1.start();
		pt2.start();
		pt3.start();
		pt4.start();
		pt5.start();
		pt6.start();
		
		C4 c1 = new C4(myStack);
		C4 c2 = new C4(myStack);
		C4 c3 = new C4(myStack);
		C4 c4 = new C4(myStack);
		C4 c5 = new C4(myStack);
		C4 c6 = new C4(myStack);
		C4 c7 = new C4(myStack);
		C4 c8 = new C4(myStack);
		CpdThreadC4 ct1 = new CpdThreadC4(c1);
		CpdThreadC4 ct2 = new CpdThreadC4(c2);
		CpdThreadC4 ct3 = new CpdThreadC4(c3);
		CpdThreadC4 ct4 = new CpdThreadC4(c4);
		CpdThreadC4 ct5 = new CpdThreadC4(c5);
		CpdThreadC4 ct6 = new CpdThreadC4(c6);
		CpdThreadC4 ct7 = new CpdThreadC4(c7);
		CpdThreadC4 ct8 = new CpdThreadC4(c8);
		ct1.start();
		ct2.start();
		ct3.start();
		ct4.start();
		ct5.start();
		ct6.start();
		ct7.start();
		ct8.start();
	}

}
//������
class P1{
	private String lock;

	public P1(String lock) {
		super();
		this.lock = lock;
	}
	public void setValue(){
		try {
			synchronized (lock) {
				if (!CpdValueObject.value.equals("")) {
					lock.wait();
				}
				String value = System.currentTimeMillis() + "_"
						+ System.nanoTime();
				System.out.println("set ��ֵ�ǣ�" + value);
				CpdValueObject.value = value;
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//������
class C1{
	private String lock;

	public C1(String lock) {
		super();
		this.lock = lock;
	}
	public void getValue(){
		try {
			synchronized (lock) {
				if (CpdValueObject.value.equals("")) {
					lock.wait();
				}
				System.out.println("get ��ֵ�ǣ�" + CpdValueObject.value);
				CpdValueObject.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class CpdValueObject{
	public static String value = "";
}
class CpdThreadP1 extends Thread{
	private P1 p;

	public CpdThreadP1(P1 p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while(true){
			p.setValue();
		}
	}
	
}
class CpdThreadC1 extends Thread{
	private C1 c;

	public CpdThreadC1(C1 c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
			c.getValue();
		}
	}
	
}
class P2{
	private String lock;

	public P2(String lock) {
		super();
		this.lock = lock;
	}
	public void setValue(){
		try {
			synchronized (lock) {
				while (!CpdValueObject.value.equals("")) {
					System.out.println("������-------"
							+ Thread.currentThread().getName() + " waiting ��");
					lock.wait();
				}
				System.out.println("������    " + Thread.currentThread().getName()
						+ "  runnable��");
				String value = System.currentTimeMillis() + "_"
						+ System.nanoTime();
				CpdValueObject.value = value;
//				lock.notify();//����ּ���
				lock.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class C2{
	private String lock;

	public C2(String lock) {
		super();
		this.lock = lock;
	}
	public void getValue(){
		try {
			synchronized (lock) {
				while (CpdValueObject.value.equals("")) {
					System.out.println("������-----"
							+ Thread.currentThread().getName() + "  waiting��");
					lock.wait();
				}
				System.out.println("������   " + Thread.currentThread().getName()
						+ "  runnable��");
				CpdValueObject.value = "";
//				lock.notify();//����ּ���
				lock.notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class CpdThreadP2 extends Thread{
	private P2 p;

	public CpdThreadP2(P2 p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while(true){
			p.setValue();
		}
	}
	
}
class CpdThreadC2 extends Thread{
	private C2 c;

	public CpdThreadC2(C2 c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
			c.getValue();
		}
	}
	
}

class MyStack1{
	private List list = new ArrayList();
	public synchronized void push(){
		try {
			if (list.size() == 1) {
				this.wait();
			}
			list.add("anyString" + Math.random());
			this.notify();
			System.out.println("push=" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized String pop(){
		String returnValue = "";
		try {
			if (list.size() == 0) {
				System.out.println("pop�����еģ� "
						+ Thread.currentThread().getName() + "�̳߳�wait״̬");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop=" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
}
class P3{
	private MyStack1 myStack;

	public P3(MyStack1 myStack) {
		super();
		this.myStack = myStack;
	}
	public void pushService(){
		myStack.push();
	}
}
class C3{
	private MyStack1 myStack;

	public C3(MyStack1 myStack) {
		super();
		this.myStack = myStack;
	}
	public void popService(){
		System.out.println("pop=" + myStack.pop());
	}
}
class CpdThreadP3 extends Thread{
	private P3 p;

	public CpdThreadP3(P3 p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while(true){
			p.pushService();
		}
	}
	
}
class CpdThreadC3 extends Thread{
	private C3 c;

	public CpdThreadC3(C3 c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
			c.popService(); 
		}
	}
}

class MyStack2{
	private List list = new ArrayList();
	public synchronized void push(){
		try {
//			if (list.size() == 1) {//������±�Խ��
			while(list.size() == 1){
				System.out.println("push�����еģ� "
						+ Thread.currentThread().getName() + "�̳߳�wait״̬");
				this.wait();
			}
			list.add("anyString" + Math.random());
//			this.notify();//����ּ���
			this.notifyAll();
			System.out.println("P   " + Thread.currentThread().getName() + "----push=" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized String pop(){
		String returnValue = "";
		try {
//			if (list.size() == 0) {//������±�Խ��
			while(list.size() == 0){
				System.out.println("pop�����еģ� "
						+ Thread.currentThread().getName() + "�̳߳�wait״̬");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
//			this.notify();//����ּ���
			this.notifyAll();
			System.out.println("C   " + Thread.currentThread().getName() + "----pop=" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnValue;
	}
}
class P4{
	private MyStack2 myStack;

	public P4(MyStack2 myStack) {
		super();
		this.myStack = myStack;
	}
	public void pushService(){
		myStack.push();
	}
}
class C4{
	private MyStack2 myStack;

	public C4(MyStack2 myStack) {
		super();
		this.myStack = myStack;
	}
	public void popService(){
		System.out.println("pop=" + myStack.pop());
	}
}
class CpdThreadP4 extends Thread{
	private P4 p;

	public CpdThreadP4(P4 p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while(true){
			p.pushService();
		}
	}
	
}
class CpdThreadC4 extends Thread{
	private C4 c;

	public CpdThreadC4(C4 c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
			c.popService(); 
		}
	}
}