package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  公平锁与非公平锁
 * 锁Lock分为“公平锁”和“非公平锁”，公平锁表示线程获取锁的顺序
 * 是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。
 * 而飞公平锁就是一种获取锁的抢占机制，是随机获得锁的，和公平锁不一样
 * 的就是先来的不一定先得到锁，这个方式可能造成某些线程一直拿不到锁，
 * 结果也就是不公平的了。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月30日 下午10:20:10 
 */
public class Fair_NoFairDemo {
	/**
	 * 打印的结果：基本是呈有序的状态，这就是公平锁的特点。
	 * @param args
	 * 2017年8月30日 下午10:33:40
	 */
	public static void main1(String[] args) {
		final FairService service = new FairService(true);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("★线程" + Thread.currentThread().getName() + "运行了");
				service.serviceMetthod();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
		}
		
		for (int i = 0; i < 10; i++) {
			threadArray[i].start();
		}
	}
	/**
	 * 非公平锁：运行结果基本上时乱序的，说明
	 * start()启动的线程不代表先获得锁。
	 * @param args
	 * 2017年8月30日 下午10:34:33
	 */
	public static void main(String[] args) {
		final FairService service = new FairService(false);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("★线程" + Thread.currentThread().getName() + "运行了");
				service.serviceMetthod();
			}
		};
		
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
		}
		
		for (int i = 0; i < 10; i++) {
			threadArray[i].start();
		}
	}
}
class FairService{
	private ReentrantLock lock;
	public FairService(boolean isFair){
		super();
		lock = new ReentrantLock(isFair);
	}
	public void serviceMetthod(){
		try {
			lock.lock();
			System.out.println("threadName=" + Thread.currentThread().getName()
					+ "获得锁定");
		}finally{
			lock.unlock();
		}
	}
}