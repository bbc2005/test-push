package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.ReentrantLock;

/** ����isFair()��isHeldByCurrentThread()��isLocked()�Ĳ���
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��9��5�� ����10:45:52 
 */
public class LockMethodTest3 {
	/**
	 * boolean isFair()���������ж��ǲ��ǹ�ƽ��
	 * ��Ĭ�ϵ�����£�ReentrantLock��ʹ�õ��Ƿǹ�ƽ����
	 * @param args
	 * 2017��9��5�� ����10:47:18
	 */
	public static void main1(String[] args) {
		final Lm3Service1 service1 = new Lm3Service1(true);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service1.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
		final Lm3Service1 service2 = new Lm3Service1(false);
		
		runnable = new Runnable() {
			@Override
			public void run() {
				service2.serviceMethod();
			}
		};
		thread = new Thread(runnable);
		thread.start();
		
	}
	/**
	 * boolean isHeldByCurrentThread()�������ǲ�ѯ��ǰ�߳�
	 * �Ƿ񱣳ִ�������
	 * @param args
	 * 2017��9��5�� ����10:55:45
	 */
	public static void main2(String[] args) {
		final Lm3Service2 service1 = new Lm3Service2(true);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service1.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
	/**
	 * boolean isLocked()�������ǲ�ѯ��
	 * �����Ƿ��������̱߳��֡�
	 * @param args
	 * 2017��9��5�� ����11:03:12
	 */
	public static void main(String[] args) {
		final Lm3Service3 service = new Lm3Service3(true);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.serviceMethod();
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
class Lm3Service1{
	private ReentrantLock lock;

	public Lm3Service1(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	public void serviceMethod(){
		try {
			lock.lock();
			System.out.println("��ƽ�������" + lock.isFair());
		} finally {
			lock.unlock();
		}
	}
}

class Lm3Service2{
	private ReentrantLock lock;

	public Lm3Service2(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod(){
		try {
			System.out.println(lock.isHeldByCurrentThread());
			lock.lock();
			System.out.println(lock.isHeldByCurrentThread());
		} finally{
			lock.unlock();
		}
	}
	
}
class Lm3Service3{
	private ReentrantLock lock;

	public Lm3Service3(boolean isFair) {
		super();
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod(){
		try {
			System.out.println(lock.isLocked());
			lock.lock();
			System.out.println(lock.isLocked());
		} finally {
			lock.unlock();
		}
	}
	
}