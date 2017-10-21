package com.lzg.booktest.t1t2;
/** 
 * 同步方法容易造成死循环。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月16日 下午10:31:50 
 */
public class ThreadDemo18 {
	/**
	 * 线程b永远得不到运行的机会 ，锁死了。
	 * @param args
	 * 2017年8月16日 下午10:37:20
	 */
	public static void main1(String[] args) {
		Td18Service1 service = new Td18Service1();
		Td18A1 a = new Td18A1(service);
		a.start();
		
		Td18B1 b = new Td18B1(service);
		b.start();
		
	}
	/**
	 * 使用同步块来解决上面的问题
	 * 这里真的解决了问题吗？用不用synchronized修饰的方法不久可以了吗，只要确保异步不久可以了？
	 * @param args
	 * 2017年8月16日 下午10:38:02
	 */
	public static void main(String[] args) {
		Td18Service2 service = new Td18Service2();
		Td18A2 a = new Td18A2(service);
		a.start();
//		Td18Service2 service1 = new Td18Service2();
//		Td18B2 b = new Td18B2(service1);//这样也可以，为什么要用同一个service？
		Td18B2 b = new Td18B2(service);
		b.start();
	}

}
class Td18Service1{
	public synchronized void methodA(){
		System.out.println("methodA begin");
		boolean isContinueRun = true;
		while(isContinueRun){
			
		}
		System.out.println("methodA end");
	}
	public synchronized void methodB(){
		System.out.println("methodB begin");
		System.out.println("methodB end");
	}
}
class Td18A1 extends Thread{
	private Td18Service1 service;

	public Td18A1(Td18Service1 service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodA();
	}
}

class Td18B1 extends Thread{
	private Td18Service1 service;

	public Td18B1(Td18Service1 service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodB();
	}
}

class Td18Service2{
	Object object1 = new Object();
	public void methodA(){
		synchronized (object1) {
			System.out.println("methodA begin");
			boolean isContinueRun = true;
			while(isContinueRun){
				System.out.println("1");
			}
			System.out.println("methodA end");
		}
	}
	
	Object object2 = new Object();
	public void methodB(){
		synchronized (object2) {//用object1也会锁死
			System.out.println("methodB begin");
			System.out.println("methodB end");
		}
	}
}
class Td18A2 extends Thread{
	private Td18Service2 service;

	public Td18A2(Td18Service2 service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodA();
	}
}

class Td18B2 extends Thread{
	private Td18Service2 service;

	public Td18B2(Td18Service2 service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodB();
	}
}