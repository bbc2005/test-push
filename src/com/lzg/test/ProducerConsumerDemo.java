package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 下午3:57:05 
 * 生产者，消费者
 * 
 * 多生产者，多消费者的问题。
 * if判断标记，只有一次，会导致不该运行的线程运行了，出现了数据错误的情况。
 * while判断标记，解决了线程获取执行权后，是否要运行！
 * 
 * notify：只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
 * notifyAll：解决了本方线程一定会唤醒对方线程的问题。
 */
public class ProducerConsumerDemo {
	public static void main(String[] args) {
		Resource3 r = new Resource3();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		
		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
}
class Producer implements Runnable{
	private Resource3 r;
	Producer(Resource3 r){
		this.r=r;
	}

	@Override
	public void run() {
		while (true) {
			r.set("烤鸭");
		}
	}
	
}
class Consumer implements Runnable{
	private Resource3 r;
	Consumer(Resource3 r){
		this.r=r;
	}

	@Override
	public void run() {
		while(true){
			r.out();
		}
	}
	
}

class Resource3{
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	public synchronized void set(String name){
//		if(flag){
		while(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.name = name + count;
		count++;
		System.out.println(Thread.currentThread().getName() + "......生产者......" + this.name);
		flag=true;
//		notify();
		notifyAll();
	}
	public synchronized void out(){
//		if(! flag){//会造成线程安全问题，一个消费多次，某个没有消费掉
		while(! flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "......消费者..." + this.name);
		flag = false;
//		notify();//while-notify:会造成死锁
		notifyAll();
	}
}
