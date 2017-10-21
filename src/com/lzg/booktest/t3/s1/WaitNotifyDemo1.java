package com.lzg.booktest.t3.s1;

import java.util.ArrayList;
import java.util.List;

/** 
 * �ȴ�/֪ͨ����
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��21�� ����10:01:03 
 */
public class WaitNotifyDemo1 {
	/**
	 * ��ʹ�õȴ�/֪ͨ����ʵ���̼߳�ͨ��
	 * �׶ˣ��߳�b��ͣ��ͨ��while�����ѯ���������ĳһ��������
	 * �������˷�CPU��Դ��
	 * �����ѯ��ʱ������С�����˷�CPU��Դ��
	 * �����ѯ��ʱ�����ܴ��п��ܻ��ղ�����Ҫ
	 * �õ������ݡ�
	 * @param args
	 * 2017��8��21�� ����10:01:30
	 */
	public static void main1(String[] args) {
		Wn1MyList service = new Wn1MyList();
		Wn1ThreadA a = new Wn1ThreadA(service);
		a.start();
		
		Wn1ThreadB b = new Wn1ThreadB(service);
		b.start();
	}
	
	/**
	 * �ȴ�/֪ͨ���Ƶ�ʵ��
	 * ����wait()��������ʹ��ǰִ�д�����߳̽��еȴ���wait()����
	 * ��Object��ķ������÷�����������ǰ�߳����롰Ԥִ�ж��С��У�������wait()
	 * ���ڵĴ����д�ִֹͣ�У�ֱ���ӵ�֪ͨ���ж�Ϊֹ���ڵ���wait()֮ǰ���̱߳���
	 * ��øö���Ķ��󼶱�������ֻ����ͬ��������ͬ�����е���wait()��������ִ��wait()
	 * �����󣬵�ǰ�߳��ͷ������ڴ�wait()����ǰ���߳��������߳̾������»�������������
	 * wait()ʱû�г����ʵ����������׳�IllegalMonitorStateException.
	 * 
	 * ����notify()ҲҪ��ͬ��������ͬ�����е��ã����ڵ���ǰ���߳�Ҳ�����øö���Ķ��󼶱�����
	 * �������notify()ʱû�г����ʵ�������Ҳ���׳�IllegalMonitorStateException��
	 * �÷�������֪ͨ��Щ���ܵȴ��ö������������̣߳�����ж���̵߳ȴ��������̹߳滮�������ѡ
	 * ������һ����wait״̬���̣߳����䷢��֪ͨnotify����ʹ���ȴ���ȡ�ö���Ķ���������Ҫע���
	 * �ǣ���ִ��notify()�����󣬵�ǰ�̲߳��������ͷŸö���������wait״̬���߳�Ҳ���������ϻ�ȡ
	 * �ö�������Ҫ�ȵ�ִ��notify()�������߳̽�����ִ���꣬Ҳ�����˳�synchronized�����󣬵�ǰ
	 * �̲߳Ż��ͷ���������wait״̬���ڵ��̲߳ſ��Ի�ȡ�ö�����������һ������˸ö�������wait�߳�����
	 * ����Ժ������ͷŵ��ö���������ʱ����ö���û���ٴ�ʹ��notify��䣬�򼴱�ö����Ѿ����У�����
	 * wait״̬�ȴ����߳�����û�еõ��ö����֪ͨ���������������wait״̬��ֱ��������󷢳�һ��notify
	 * ��notifyAll.
	 * 
	 * waitʹ�߳�ֹͣ���С�
	 * notifyʹֹͣ���̼߳������С�
	 * 
	 */
	
	/**
	 * �쳣ԭ��û�С��������������Ҳ����û��ͬ��������
	 * @param args
	 * 2017��8��21�� ����10:23:17
	 */
	public static void main2(String[] args) {
		try {
			String newString = new String("");
			newString.wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��Զ�ڵȴ�
	 * @param args
	 * 2017��8��21�� ����10:46:18
	 */
	public static void main3(String[] args) {
		try {
			String lock = new String();
			System.out.println("syn����");
			synchronized (lock) {
				System.out.println("syn��һ��");
				lock.wait();
				System.out.println("wait�µĴ��룡");
			}
			System.out.println("syn����Ĵ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main4(String[] args) {
		try {
			Object lock = new Object();
			Wn1Thread1 t1 = new Wn1Thread1(lock);
			t1.start();
			Thread.sleep(3000);
			Wn1Thread2 t2 = new Wn1Thread2(lock);
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			WnThreadA1 a = new WnThreadA1(lock);
			a.start();
			Thread.sleep(50);
			WnThreadB1 b = new WnThreadB1(lock);
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class Wn1MyList{
	private List list = new ArrayList();
	public void add(){
		list.add("asd");
	}
	public int size(){
		return list.size();
	}
}
class Wn1ThreadA extends Thread{
	private Wn1MyList list;

	public Wn1ThreadA(Wn1MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				list.add();
				System.out.println("�����" + (i + 1) + "��Ԫ��");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Wn1ThreadB extends Thread{
	private Wn1MyList list;

	public Wn1ThreadB(Wn1MyList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while(true){
				if(list.size() == 5){
					System.out.println("==5�ˣ��߳�bҪ�˳��ˣ�");
					throw new InterruptedException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Wn1Thread1 extends Thread{
	private Object lock;

	public Wn1Thread1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println("��ʼ                 wait  time ="
						+ System.currentTimeMillis());
				lock.wait();
				System.out.println("����                  wait  time ="
						+ System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Wn1Thread2 extends Thread{
	private Object lock;

	public Wn1Thread2(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {   
		synchronized(lock){
			System.out.println("��ʼnotify time=" + System.currentTimeMillis());
			lock.notify();
			System.out.println("����notify time=" + System.currentTimeMillis());
		}
	}
	
}
class WnMyList1{
	private static List list = new ArrayList();
	public static void add(){
		list.add("anyString");
	}
	public static int size(){
		return list.size();
	}
}
class WnThreadA1 extends Thread{
	private Object lock;

	public WnThreadA1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				if (WnMyList1.size() != 5) {
					System.out.println("wait begin "
							+ System.currentTimeMillis());
					lock.wait();
					System.out
							.println("wait end " + System.currentTimeMillis());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class WnThreadB1 extends Thread{
	private Object lock;

	public WnThreadB1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					WnMyList1.add();
					if (WnMyList1.size() == 5) {
						lock.notify();
						System.out.println("�ѷ���֪ͨ��");
					}
					System.out.println("�����" + (i + 1) + "��Ԫ�أ�");
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}