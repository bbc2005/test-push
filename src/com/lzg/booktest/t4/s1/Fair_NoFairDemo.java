package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  ��ƽ����ǹ�ƽ��
 * ��Lock��Ϊ����ƽ�����͡��ǹ�ƽ��������ƽ����ʾ�̻߳�ȡ����˳��
 * �ǰ����̼߳�����˳��������ģ��������ȵõ�FIFO�Ƚ��ȳ�˳��
 * ���ɹ�ƽ������һ�ֻ�ȡ������ռ���ƣ������������ģ��͹�ƽ����һ��
 * �ľ��������Ĳ�һ���ȵõ����������ʽ�������ĳЩ�߳�һֱ�ò�������
 * ���Ҳ���ǲ���ƽ���ˡ�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��30�� ����10:20:10 
 */
public class Fair_NoFairDemo {
	/**
	 * ��ӡ�Ľ���������ǳ������״̬������ǹ�ƽ�����ص㡣
	 * @param args
	 * 2017��8��30�� ����10:33:40
	 */
	public static void main1(String[] args) {
		final FairService service = new FairService(true);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("���߳�" + Thread.currentThread().getName() + "������");
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
	 * �ǹ�ƽ�������н��������ʱ����ģ�˵��
	 * start()�������̲߳������Ȼ������
	 * @param args
	 * 2017��8��30�� ����10:34:33
	 */
	public static void main(String[] args) {
		final FairService service = new FairService(false);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("���߳�" + Thread.currentThread().getName() + "������");
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
					+ "�������");
		}finally{
			lock.unlock();
		}
	}
}