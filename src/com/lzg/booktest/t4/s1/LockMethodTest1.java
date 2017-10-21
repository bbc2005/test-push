package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * ����getHoldCount()��getQueueLength()��getWaitQueueLength()�Ĳ���
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��31�� ����10:19:40 
 */
public class LockMethodTest1 {

	/**
	 * int getHoldCount()�������ǲ�ѯ��ǰ�̱߳���
	 * �������ĸ�����Ҳ���ǵ���lock()�����Ĵ�����
	 * @param args
	 * 2017��8��31�� ����10:20:59
	 */
	public static void main1(String[] args) {
		LmtService1 service = new LmtService1();
		service.serviceMethod1();
	}
	/**
	 * ����int getQueueLength()��������
	 * �������ȴ���ȡ���������̹߳�������
	 * @param args
	 * 2017��8��31�� ����10:41:55
	 * @throws InterruptedException 
	 */
	public static void main2(String[] args) throws InterruptedException {
		final LmtService2 service = new LmtService2();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.serviceMethod1();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
			threadArray[i].start();
		}
		Thread.sleep(2000);
		System.out.println("���߳�����" + service.lock.getQueueLength() + "�ڵȴ���ȡ����");
	}
	/**
	 * ���� int getWaitQueueLength(Condition condition)��������
	 * ���صȴ����������صĸ�������Condition���̹߳�������
	 * @param args
	 * 2017��8��31�� ����10:51:46
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final LmtService3 service = new LmtService3();
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		Thread[] threadArray = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threadArray[i] = new Thread(runnable);
			threadArray[i].start();
		}
		Thread.sleep(2000);
		service.notityMethod();
	}

}
class LmtService1{
	private ReentrantLock lock = new ReentrantLock();
	public void serviceMethod1(){
		try {
			lock.lock();
			System.out.println("serviceMethod1 getHoldCount="
					+ lock.getHoldCount());
			serviceMethod2();
		} finally{
			lock.unlock();
		}
	}
	public void serviceMethod2(){
		try {
			lock.lock();
			System.out.println("serviceMethod2 getHoldCount="
					+ lock.getHoldCount());
//			serviceMethod1();
		} finally{
			lock.unlock();
		}
	}
}

class LmtService2{
	public ReentrantLock lock = new ReentrantLock();
	public void serviceMethod1(){
		try {
			lock.lock();
			System.out.println("ThreadName=" + Thread.currentThread().getName()
					+ "���뷽����");
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}
class LmtService3{
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
			System.out.println("��" + lock.getWaitQueueLength(condition)
					+ "���߳����ڵȴ�condition");
			condition.signal();
		} finally{
			lock.unlock();
		}
	}
}