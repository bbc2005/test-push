package com.lzg.booktest.t1t2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/** 
 * 关键字volatile的主要作用是使变量在多个线程间可见。
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月19日 下午4:52:11 
 * 
 * 使用volatile关键字增加了实例变量在多个线程之间的可见性，但
 * volatile关键字最致命的缺点是不支持原子性。
 * 
 * 关键字synchronized和volatile的区别：
 * 1：volatile是线程同步的轻量级实现，所以volatile性能肯定比synchronized要好，
 * 并且volatile只能修饰于变量，而synchronized可以修饰方法，以及代码块。随着JDK新
 * 版本的发布，synchronized关键字在执行效率上得到很大提升，在开发中使用synchronized
 * 关键字的比率还是比较大的。
 * 2：多线程访问volatile不会发生阻塞，而synchronized会出现阻塞。
 * 3：volatile能保证数据的可见性，但不能保证原子性；而synchronized
 * 可以保证原子性，也可以间接保证可见性，因为它会将私有内存和公共内存中的数据做同步。
 * 4：volatile解决的是变量在多个线程之间的可见性；而synchronized解决的是多个
 * 线程之间访问资源的同步性。
 * 
 * 线程安全包含原子性和可见性两个方面，Java的同步机制都是围绕着两个方面来确保线程
 * 安全的。
 * 
 * volatile主要使用的场合是在多个线程中可以感知实例变量被更改了，并且可以获得
 * 最新的值使用，也就是用多线程读取共享变量时可以获得最新值使用。
 * volatile提示线程每次从共享内存中读取变量，而不是从私有内存中读取，这样就
 * 保证了同步数据的可见性。
 * 
 * synchronized可以保证在同一时刻，只有一个线程可以执行某一个方法或某一个
 * 代码块。它包含两个特征：互斥性和可见性。同步synchronized不仅可以解决一个
 * 线程看到对象处于不一致的状态，还可以保证进入同步方法或者同步代码块的每个线程
 * ，都看到由同一个锁保护之前所有的修改效果。
 * 学习多线程并发，要着重“外练互斥，内修可见”。
 */
public class VolatileThreadDemo {
	/**
	 * 停不下来的原因是：main线程一直在处理while()循环，导致
	 * 程序不能继续执行后面的代码。
	 * 解决办法：使用多线程技术
	 * @param args
	 * 2017年8月19日 下午5:18:34
	 */
	public static void main1(String[] args) {
		VtdPrintString vps = new VtdPrintString();
		vps.printStringMethod();
		System.out.println("我要停止它！ stopThread=" + Thread.currentThread().getName());
		vps.setContinuePrint(false);
	}
	/**
	 * 解决同步死循环
	 * 在部分JVM上会出现死循环
	 * 解决办法：使用volatile关键字
	 * @param args
	 * 2017年8月19日 下午5:24:04
	 */
	public static void main2(String[] args) {
		VtdPrintString1 vps = new VtdPrintString1();
		new Thread(vps).start();
		System.out.println("我要停止它！ stopThread=" + Thread.currentThread().getName());
		vps.setContinuePrint(false);
	}
	
	/**
	 * 解决异步死循环
	 * 关键字volatile的作用是强制从公共堆栈中取得变量的值，而不是
	 * 从线程私有数据栈中取得变量的值。
	 */
	
	/**
	 * 将JVM设置为 -server时出现死循环
	 * 在启动VtdRunThread 线程时，变量private boolean isRunning =true;存在于
	 * 公共堆栈及线程的私有堆栈中。在JVM被设置为-server模式时为了线程的运行的效率，线程
	 * 一直在私有堆栈中取得isRunning的值是true。而代码t.setRunning(false);虽然被
	 * 执行，更新的却是公共堆栈中的isRunning变量值false,所有一直就是死循环的状态。
	 * @param args
	 * 2017年8月19日 下午5:26:31
	 * @throws InterruptedException 
	 */
	public static void main3(String[] args) throws InterruptedException {
		VtdRunThread t = new VtdRunThread();
		t.start();
		Thread.sleep(1000);
		t.setRunning(false);
		System.out.println("已经赋值为false");
	}
	/**
	 * volatile虽然增加了实例变量在多个线程之间的可见性，但它却不具备
	 * 同步性，那么也就不具备原子性。
	 * @param args
	 * 2017年8月20日 上午11:46:49
	 */
	public static void main4(String[] args) {
		VolatileTestThread[] vttArray = new VolatileTestThread[100];
		for (int i = 0; i < vttArray.length; i++) {
			vttArray[i] = new VolatileTestThread();
			vttArray[i].setName("a" + i);
		}
		for (int i = 0; i < vttArray.length; i++) {
			vttArray[i].start();
		}
	}
	/**
	 * 使用原子类进行i++操作
	 * 
	 * 原子操作是不能分割的整体，没有其他线程能够中断或检查正在原子操作的
	 * 变量。一个原子（atomic）类型就是一个原子操作可用的类型，它可以在
	 * 没有锁的情况下做到线程安全（thread-safe）。
	 * @param args
	 * 2017年8月20日 下午3:26:12
	 */
	public static void main5(String[] args) {
		
		AtomicIntegerTest t = new AtomicIntegerTest();
		
		Thread t1 = new Thread(t);
		t1.setName("t1");
		t1.start();
		Thread t2 = new Thread(t);
		t2.setName("t2");
		t2.start();
		Thread t3 = new Thread(t);
		t3.setName("t3");
		t3.start();
		Thread t4 = new Thread(t);
		t4.setName("t4");
		t4.start();
		Thread t5 = new Thread(t);
		t5.setName("t5");
		t5.start();
		
	}
	/**
	 * 原子类也并不完全安全
	 * 
	 * 原子类在具有逻辑性的情况下输出结果也具有随机性。
	 * 
	 * 本例：打印顺序出错了，应该每加1次100再加1次1，
	 * 出现这样的情况是因为addAndGet()方法是原子的，
	 * 但方法和方法之间的调用却不是原子的。解决这样的
	 * 问题必须要用同步。
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月20日 下午3:39:47
	 */
	public static void main6(String[] args) throws InterruptedException {
		AtomicIntergerNoSafe ai = new AtomicIntergerNoSafe();
		AtomicThread[] array = new AtomicThread[5];
		for (int i = 0; i < array.length; i++) {
			array[i] = new AtomicThread(ai);
		}
		for (int i = 0; i < array.length; i++) {
			array[i].start();
		}
		
		Thread.sleep(1000);
		System.out.println(ai.aiRef.get());
	}
	/**
	 * synchronized代码块有volatile同步的功能
	 * 
	 * synchronized 可以使多个线程访问同一个资源具有同步性，而且它还具有将线程工作内存中的
	 * 私有变量与公共内存中的变量同步的功能。
	 * 
	 * 以-server服务器模式运行，出现死循环：得到这个结果
	 * 是各线程间的数据值没有可视性造成的，而synchronized可以具有可视性。
	 * 
	 * @param args
	 * @throws InterruptedException
	 * 2017年8月20日 下午3:57:03
	 */
	public static void main(String[] args) throws InterruptedException {
		SynchronizedUpdateNewValue service = new SynchronizedUpdateNewValue();
		SynchronizedUpdateNewValueThreadA a = new SynchronizedUpdateNewValueThreadA(service);
		a.start();
		Thread.sleep(1000);
		SynchronizedUpdateNewValueThreadB b = new SynchronizedUpdateNewValueThreadB(service);
		b.start();
		System.out.println("已经发起停止的命令！");
	}

}
class VtdPrintString{
	private boolean isContinuePrint =  true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	public void printStringMethod(){
		try {
			while (isContinuePrint == true) {
				System.out.println("run printStringMethod threadName="
						+ Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class VtdPrintString1 implements Runnable{
	private boolean isContinuePrint =  true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	public void printStringMethod(){
		try {
			while (isContinuePrint == true) {
				System.out.println("run printStringMethod threadName="
						+ Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		printStringMethod();
	}
}

class VtdRunThread extends Thread{
//	private boolean isRunning = true;//将JVM设置为 -server时出现死循环
	private volatile boolean isRunning = true;//将JVM设置为 -server时不会出现死循环，强制的从公告内存中读取变量的值
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		System.out.println("进入 run 了");
		while(isRunning == true){
			
		}
		System.out.println("线程被停止了！");
	}
	
}

class VolatileTestThread extends Thread{
	public volatile static int count;
	
//	private static void addCount(){//不同步
	//这里使用synchronized，也就没有必要使用volatile来声明count变量了
	private synchronized static void addCount(){//一定要添加static，这样synchronized与static锁的内容就是类的了，也就达到同步的效果了
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("threadName=" + Thread.currentThread().getName() + "       count=" + count);
	}
	@Override
	public void run() {
		addCount();
	}
}

class AtomicIntegerTest extends Thread{
	private AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("threadName=" + Thread.currentThread().getName() + "-------------->count=" + count.incrementAndGet());
		}
	}
}
class AtomicIntergerNoSafe{
	public static AtomicLong aiRef = new AtomicLong();
//	public void addNum(){//不同步
	public synchronized void addNum(){//同步
		System.out.println(Thread.currentThread().getName() + "加了100之后的值是：" + aiRef.addAndGet(100));
		aiRef.addAndGet(1);
	}
}
class AtomicThread extends Thread{
	private AtomicIntergerNoSafe ai;

	public AtomicThread(AtomicIntergerNoSafe ai) {
		super();
		this.ai = ai;
	}

	@Override
	public void run() {
		ai.addNum();
	}
	
}

class SynchronizedUpdateNewValue{
	private boolean isContinueRun = true;
	//没有可视性
	/*public void runMethod(){
		while(isContinueRun == true){
			
		}
		System.out.println("停下来了！");
	}*/
	//修改后有可视性
	public void runMethod(){
		String anyString = new String();
		while(isContinueRun == true){
			synchronized (anyString) {
				
			}
		}
		System.out.println("停下来了！");
	}
	public void stopMethod(){
		isContinueRun = false;
	}
}
class SynchronizedUpdateNewValueThreadA extends Thread{
	private SynchronizedUpdateNewValue service;

	public SynchronizedUpdateNewValueThreadA(SynchronizedUpdateNewValue service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.runMethod();
	}
	
}

class SynchronizedUpdateNewValueThreadB extends Thread{
	private SynchronizedUpdateNewValue service;

	public SynchronizedUpdateNewValueThreadB(SynchronizedUpdateNewValue service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.stopMethod();
	}
	
}