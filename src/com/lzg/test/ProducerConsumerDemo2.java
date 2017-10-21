package com.lzg.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��6�� ����3:57:05 
 * 
 * ͬ������飬�������Ĳ�������ʽ��
 * jdk1.5�Ժ�ͬ��������װ���˶���
 * ��������������ʽ��ʽ���嵽�˸ö����У�
 * ����ʽ�����������ʾ������
 * 
 * Lock�ӿڣ����������ͬ����������ͬ����������ͬ������ʽ������
 * �����ʽ��������ͬʱ��Ϊ������һ�����ϼ��϶����������
 * lock()����ȡ����
 * unlock()���ͷ�����ͨ����Ҫ������finally������С�
 * 
 * Condition�ӿڣ����������Object�е�wait notify notifyAll������
 * ����Щ�������������������˷�װ�����Condition����������
 * ����������������ϡ�
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
			r.set("��Ѽ");
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
	//����һ��������
	Lock lock = new ReentrantLock();
	//ͨ�����е�����ȡ�����ϵļ���������,һ����һ�������
//	Condition con = lock.newCondition();
	
	//ͨ�����е�����ȡ�����������һ����������ߣ�һ�����������
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
			System.out.println(Thread.currentThread().getName() + "......������5.0......" + this.name);
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
			System.out.println(Thread.currentThread().getName() + "......������5.0..." + this.name);
			flag = false;
//			con.signalAll();//con.signal();//���������
			producer_con.signal();
		} catch (Exception e) {
		}finally{
			lock.unlock();
		}
	}
}
