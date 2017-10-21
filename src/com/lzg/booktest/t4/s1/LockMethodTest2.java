package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** ����hasQueuedThread()��hasQueuedThreads()��hasWaiters()
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��9��3�� ����9:29:48 
 */
public class LockMethodTest2 {
	/**
	 * boolean hasQueuedThread(Thread thread)�������ǲ�ѯָ��
	 * ���߳��Ƿ����ڵȴ���ȡ��������
	 * boolean hasQueuedThreads()�������ǲ�ѯ�Ƿ����߳����ڵȴ���
	 * ȡ��������
	 * 
	 * @param args
	 * 2017��9��3�� ����9:31:12
	 * @throws InterruptedException 
	 */
	public static void main1(String[] args) throws InterruptedException {
		final LmService1 service = new LmService1();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.start();
		
		Thread.sleep(500);
		
		Thread b = new Thread(runnable);
		b.start();
		
		Thread.sleep(500);
		
		System.out.println(service.lock.hasQueuedThread(a));
		System.out.println(service.lock.hasQueuedThread(b));
		System.out.println(service.lock.hasQueuedThreads());
	}
	/**
	 * ����boolean hasWaiters(Condition condition)������
	 * �ǲ�ѯ�Ƿ����߳����ڵȴ���������йص�condition����
	 * @param args
	 * 2017��9��3�� ����9:40:34
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final LmService2 service = new LmService2();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i] = new Thread(runnable);
		}
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i].start();
		}
		
		Thread.sleep(2000);
		service.notityMethod();
	}

}
class LmService1{
	public ReentrantLock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			lock.lock();
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
class LmService2{
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			lock.lock();
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void notityMethod(){
		try {
			lock.lock();
			System.out.println("��û���߳����ڵȴ�condition?"
					+ lock.hasWaiters(condition) + " �߳����Ƕ��٣�"
					+ lock.getWaitQueueLength(condition));
			condition.signalAll();
		} catch (Exception e) {
			lock.unlock();
		}
	}
}