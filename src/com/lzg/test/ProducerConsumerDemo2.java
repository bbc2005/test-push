package com.lzg.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 下午3:57:05 
 * 
 * 同步代码块，对于锁的操作是隐式的
 * jdk1.5以后将同步和锁封装成了对象。
 * 并将操作锁的隐式方式定义到了该对象中，
 * 将隐式动作变成了显示动作。
 * 
 * Lock接口：出现替代了同步代码块或者同步函数。将同步的隐式锁操作
 * 变成显式锁操作。同时更为灵活。可以一个锁上加上多组监视器。
 * lock()：获取锁。
 * unlock()：释放锁，通常需要定义在finally代码块中。
 * 
 * Condition接口：出现替代了Object中的wait notify notifyAll方法。
 * 将这些监视器方法单独进行了封装，变成Condition监视器对象，
 * 可以任意锁进行组合。
 * await();
 * signal();
 * signalAll();
 * 
 */
public class ProducerConsumerDemo2 {
	public static void main(String[] args) {
		Resource4 r = new Resource4();
		Producer1 pro = new Producer1(r);
		Consumer1 con = new Consumer1(r);
		
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
class Producer1 implements Runnable{
	private Resource4 r;
	Producer1(Resource4 r){
		this.r=r;
	}

	@Override
	public void run() {
		while (true) {
			r.set("烤鸭");
		}
	}
	
}
class Consumer1 implements Runnable{
	private Resource4 r;
	Consumer1(Resource4 r){
		this.r=r;
	}

	@Override
	public void run() {
		while(true){
			r.out();
		}
	}
	
}

class Resource4{
	private String name;
	private int count = 1;
	private boolean flag = false;
	//创建一个锁对象
	Lock lock = new ReentrantLock();
	//通过已有的锁获取该锁上的监视器对象,一个锁一组监视器
//	Condition con = lock.newCondition();
	
	//通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者
	Condition producer_con = lock.newCondition();
	Condition consumer_con = lock.newCondition();
	
	public void set(String name){
		lock.lock();
		try {
			while(flag){
				try {
//					con.await();
					producer_con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName() + "......生产者5.0......" + this.name);
			flag=true;
//			con.signalAll();
			consumer_con.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	public void out(){
		lock.lock();
		try {
			while(! flag){
				try {
//					con.await();
					consumer_con.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "......消费者5.0..." + this.name);
			flag = false;
//			con.signalAll();//con.signal();//会造成死锁
			producer_con.signal();
		} catch (Exception e) {
		}finally{
			lock.unlock();
		}
	}
}
