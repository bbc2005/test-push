package com.lzg.booktest.t3.s2;

import java.util.Date;


/** 
 * ��ThreadLocal��Ҫ���ÿ���̰߳��Լ���ֵ��
 * ���Խ�ThreadLocal�������ȫ�ִ�����ݵĺ���
 * �������п��Դ洢ÿ���̵߳�˽�����ݡ�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��27�� ����9:50:56 
 */
public class ThreadLocalDemo {
	
	public static ThreadLocal tl = new ThreadLocal();

	/**
	 * ��ThreadLocal������Ǳ����ڲ�ͬ�̼߳��
	 * �����ԣ�Ҳ���ǲ�ͬ�߳�ӵ���Լ���ֵ����ͬ�߳���
	 * ��ֵ�ǿ��Է���ThreadLocal���н��б���ġ�
	 * @param args
	 * 2017��8��27�� ����9:56:10
	 */
	public static void main1(String[] args) {
		if(tl.get() == null){
			System.out.println("��δ�Ź�ֵ");
			tl.set("�ҵ�ֵ");
		}
		System.out.println(tl.get());
		System.out.println(tl.get());
	}
	/**
	 * ��֤�̱߳����ĸ�����
	 * @param args
	 * 2017��8��27�� ����10:06:13
	 */
	public static void main2(String[] args) {
		try {
			TlThreadA a = new TlThreadA();
			TlThreadB b = new TlThreadB();
			a.start();
			b.start();
			for (int i = 0; i < 100; i++) {
				ThreadLocalTestTools.tl.set("Main" + (i + 1));
				System.out.println("Main get Value="
						+ ThreadLocalTestTools.tl.get());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ٴ���֤���ݵĸ�����
	 * @param args
	 * 2017��8��27�� ����10:14:29
	 */
	public static void main3(String[] args) {
		try {
			TlThread1 a = new TlThread1();
			a.start();
			Thread.sleep(1000);
			TlThread2 b = new TlThread2();
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ThreadLocalExt ext = new ThreadLocalExt();
	/**
	 *���get()����null����
	 * @param args
	 * 2017��8��27�� ����10:16:11
	 */
	public static void main4(String[] args) {
		if(ext.get() == null){
			System.out.println("��δ�Ź�ֵ");
			ext.set("�ҵ�ֵ");
		}
		System.out.println(ext.get());
		System.out.println(ext.get());
	}
	/**
	 * �ٴ���֤�̱߳����ĸ�����
	 * ���̺߳͸��̸߳��и�����ӵ�е�ֵ��
	 * @param args
	 * 2017��8��27�� ����10:20:53
	 */
	public static void main5(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��Main�߳���ȡֵ=" + TlTools.ext.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			TlThread3 a = new TlThread3();
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ʹ����InheritableThreadLocal���������߳���ȡ�ø��̼̳߳�������ֵ��
	 * 
	 */
	/**
	 * ֵ�̳У�
	 * ʹ��InheritableThreadLocal����������̴߳Ӹ��߳���ȡ��ֵ��
	 * @param args
	 * 2017��8��27�� ����10:30:30
	 */
	public static void main6(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��Main�߳���ȡֵ=" + InheriTools.ext.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			InheriThreadA a = new InheriThreadA();
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ֵ�̳����޸�
	 * �ڼ̳е�ͬʱ�����Զ�ֵ���н�һ���Ĵ���
	 * @param args
	 * 2017��8��27�� ����10:45:04
	 */
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��Main�߳���ȡֵ=" + InheriTools1.ext.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			InheriThreadB a = new InheriThreadB();
			a.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class ThreadLocalTestTools{
	public static ThreadLocal tl = new ThreadLocal();
}
class TlThreadA extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				ThreadLocalTestTools.tl.set("ThreadA" + (i + 1));
				System.out.println("ThreadA get Value="
						+ ThreadLocalTestTools.tl.get());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class TlThreadB extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				ThreadLocalTestTools.tl.set("ThreadB" + (i + 1));
				System.out.println("ThreadB get Value="
						+ ThreadLocalTestTools.tl.get());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
class ThreadLocalTools{
	public static ThreadLocal<Date> tl = new ThreadLocal<Date>();
}

class TlThread1 extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				if (ThreadLocalTools.tl.get() == null) {
					ThreadLocalTools.tl.set(new Date());
				}
				System.out.println("A  " + ThreadLocalTools.tl.get().getTime());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class TlThread2 extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				if (ThreadLocalTools.tl.get() == null) {
					ThreadLocalTools.tl.set(new Date());
				}
				System.out.println("B  " + ThreadLocalTools.tl.get().getTime());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
class ThreadLocalExt extends ThreadLocal{

	@Override
	protected Object initialValue() {
		return "����Ĭ��ֵ  ��һ��get����Ϊnull";
	}
	
}

class TlTools{
	public static TlExt ext = new TlExt();
}
class TlExt extends ThreadLocal{

	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
	
}

class TlThread3 extends Thread{

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��A�߳���ȡֵ=" + TlTools.ext.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class InheritableThreadLocalExt extends InheritableThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
}
class InheriTools{
	public static InheritableThreadLocalExt ext = new InheritableThreadLocalExt();
}
class InheriThreadA extends Thread{
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��A�߳���ȡֵ=" + InheriTools.ext.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class InheritableThreadLocalExt1 extends InheritableThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}

	@Override
	protected Object childValue(Object parentValue) {
		return parentValue + " �������̼߳ӵ�~��";
	}
	
}
class InheriTools1{
	public static InheritableThreadLocalExt1 ext = new InheritableThreadLocalExt1();
}
class InheriThreadB extends Thread{
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("��A�߳���ȡֵ=" + InheriTools1.ext.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}