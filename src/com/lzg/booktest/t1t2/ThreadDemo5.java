package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 上午10:40:11 
 * 暂停线程：
 * 使用suspend()方法暂停线程，使用resume()方法恢复线程的执行。
 * 
 */
public class ThreadDemo5 {

	public static void main1(String[] args) {
		try {
			ThreadTest5 t = new ThreadTest5();
			t.start();
			Thread.sleep(5000);
			//A段
			t.suspend();
			System.out.println("A=" + System.currentTimeMillis() + " i="
					+ t.getI());
			Thread.sleep(5000);
			System.out.println("A=" + System.currentTimeMillis() + " i="
					+ t.getI());
			//B段
			t.resume();
			Thread.sleep(5000);
			//C段
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
	//suspend与resume方法的缺点：独占
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
					System.out.println("t2启动了，但进入不了printString()方法！只打印1个begin");
					System.out
							.println("因为printString()方法被a线程锁定并且永远suspend暂停了！");
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
 * 使用suspend与resume方法时，如果使用不当，极易造成公共
 * 的同步对象的独占，使得其他线程无法访问公共同步对象。
 * @author DELL
 *
 */
class ThreadTest51 extends Thread{
	synchronized public void printString(){
		System.out.println("begin");
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("a线程永远suspend了！");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}
/**
 * 当程序运行到print()方法内部停止时，同步锁未被释放。
 * 导致当前PrintStream对象的println()方法一直呈“暂停”状态，
 * 并且“锁未释放”，而main()方法中的代码System.out.println("main end!")，迟迟不能执行打印。
 *
 */
class ThreadTest52 extends Thread{
	private long i = 0;
	@Override
	public void run() {
		while(true){
			i++;
			System.out.println(i);//控制台将不打印main end
		}
	}
}
/**
 * suspend 与 resume 方法的缺点：不同步
 *
 */
class ThreadTest53 extends Thread{
	private String username = "1";
	private String password = "11";
	public void setValue(String u,String p){
		this.username = u;
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("停止a线程！");
			Thread.currentThread().suspend();
		}
		this.password = p;
	}
	public void print(){
		System.out.println(username + " " +password);
	}
}