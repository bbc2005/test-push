package com.lzg.booktest.t4.s1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/** ����lockInterruptibly()��tryLock()��tryLock(long timeout,TimmeUnit,unit)
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��9��6�� ����10:19:33 
 */
public class LockInterruptibleyTest1 {
	/**
	 * void lockInterruptibly()�����ã������ǰ�߳�δ���жϣ�
	 * ���ȡ����������Ѿ����ж�������쳣��
	 * @param args
	 * 2017��9��6�� ����10:20:49
	 * @throws InterruptedException 
	 */
	public static void main1(String[] args) throws InterruptedException {
		final litService1 service = new litService1();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread.sleep(500);
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
		
		b.interrupt();//����
		System.out.println("main end !");
	}
	/**
	 * boolean tryLock()�����ã����ڵ���ʱ����δ����һ���̱߳��ֵ�
	 * ����£��Ż�ȡ��������
	 * @param args
	 * 2017��9��6�� ����10:40:56
	 */
	public static void main2(String[] args) {
		final LitService2 service = new LitService2();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
	}
	/**
	 * boolean tryLock(long timeout,TimeUnit unit)�����ã�
	 * ��������ڸ����ȴ�ʱ����û�б���һ���̱߳��֣��ҵ�ǰ�߳�δ���жϣ�
	 * ���ȡ��������
	 * @param args
	 * 2017��9��6�� ����10:47:09
	 */
	public static void main(String[] args) {
		final LitService3 service = new LitService3();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "����waitMethodʱ�䣺" + System.currentTimeMillis());
				service.waitMethod();
			}
		};
		
		Thread a = new Thread(runnable);
		a.setName("A");
		a.start();
		
		Thread b = new Thread(runnable);
		b.setName("B");
		b.start();
	}
}
class litService1{
	public ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				System.out.println("catch----------");
				e.printStackTrace();
			}
//			lock.lock();//��������������lock.lockInterruptibly()���쳣
			System.out
					.println("lock begin " + Thread.currentThread().getName());
			for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
				String newString = new String();
				Math.random();
			}
			System.out.println("lock  end " + Thread.currentThread().getName());
		} finally{
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}
}
class LitService2{
	public ReentrantLock lock = new ReentrantLock();
	public void waitMethod(){
		if(lock.tryLock()){
			System.out.println(Thread.currentThread().getName() + "�����");
		}else{
			System.out.println(Thread.currentThread().getName() + "û�л����");
		}
	}
}

class LitService3{
	public ReentrantLock lock = new ReentrantLock();
	public void waitMethod(){
		try {
			if (lock.tryLock(3, TimeUnit.SECONDS)) {
				System.out.println("      " + Thread.currentThread().getName()
						+ " �������ʱ�䣺" + System.currentTimeMillis());
				Thread.sleep(10000);
			} else {
				System.out.println("      " + Thread.currentThread().getName()
						+ " û�л����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}
}