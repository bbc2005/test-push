package com.lzg.booktest.t3.s2;

import java.util.Date;


/** 
 * 类ThreadLocal主要解决每个线程绑定自己的值，
 * 可以将ThreadLocal类比喻成全局存放数据的盒子
 * ，盒子中可以存储每个线程的私有数据。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月27日 下午9:50:56 
 */
public class ThreadLocalDemo {
	
	public static ThreadLocal tl = new ThreadLocal();

	/**
	 * 类ThreadLocal解决的是变量在不同线程间的
	 * 隔离性，也就是不同线程拥有自己的值，不同线程中
	 * 的值是可以放入ThreadLocal类中进行保存的。
	 * @param args
	 * 2017年8月27日 下午9:56:10
	 */
	public static void main1(String[] args) {
		if(tl.get() == null){
			System.out.println("从未放过值");
			tl.set("我的值");
		}
		System.out.println(tl.get());
		System.out.println(tl.get());
	}
	/**
	 * 验证线程变量的隔离性
	 * @param args
	 * 2017年8月27日 下午10:06:13
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
	 * 再次验证数据的隔离性
	 * @param args
	 * 2017年8月27日 下午10:14:29
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
	 *解决get()返回null问题
	 * @param args
	 * 2017年8月27日 下午10:16:11
	 */
	public static void main4(String[] args) {
		if(ext.get() == null){
			System.out.println("从未放过值");
			ext.set("我的值");
		}
		System.out.println(ext.get());
		System.out.println(ext.get());
	}
	/**
	 * 再次验证线程变量的隔离性
	 * 子线程和父线程各有各自所拥有的值。
	 * @param args
	 * 2017年8月27日 下午10:20:53
	 */
	public static void main5(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("在Main线程中取值=" + TlTools.ext.get());
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
	 * 使用类InheritableThreadLocal可以在子线程中取得父线程继承下来的值。
	 * 
	 */
	/**
	 * 值继承：
	 * 使用InheritableThreadLocal类可以让子线程从父线程中取得值。
	 * @param args
	 * 2017年8月27日 下午10:30:30
	 */
	public static void main6(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("在Main线程中取值=" + InheriTools.ext.get());
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
	 * 值继承在修改
	 * 在继承的同时还可以对值进行进一步的处理。
	 * @param args
	 * 2017年8月27日 下午10:45:04
	 */
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("在Main线程中取值=" + InheriTools1.ext.get());
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
		return "我是默认值  第一次get不再为null";
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
				System.out.println("在A线程中取值=" + TlTools.ext.get());
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
				System.out.println("在A线程中取值=" + InheriTools.ext.get());
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
		return parentValue + " 我在子线程加的~！";
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
				System.out.println("在A线程中取值=" + InheriTools1.ext.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}