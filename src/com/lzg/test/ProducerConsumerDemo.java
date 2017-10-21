package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��6�� ����3:57:05 
 * �����ߣ�������
 * 
 * �������ߣ��������ߵ����⡣
 * if�жϱ�ǣ�ֻ��һ�Σ��ᵼ�²������е��߳������ˣ����������ݴ���������
 * while�жϱ�ǣ�������̻߳�ȡִ��Ȩ���Ƿ�Ҫ���У�
 * 
 * notify��ֻ�ܻ���һ���̣߳�������������˱�����û�����塣����while�жϱ��+notify�ᵼ��������
 * notifyAll������˱����߳�һ���ỽ�ѶԷ��̵߳����⡣
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
			r.set("��Ѽ");
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
		System.out.println(Thread.currentThread().getName() + "......������......" + this.name);
		flag=true;
//		notify();
		notifyAll();
	}
	public synchronized void out(){
//		if(! flag){//������̰߳�ȫ���⣬һ�����Ѷ�Σ�ĳ��û�����ѵ�
		while(! flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "......������..." + this.name);
		flag = false;
//		notify();//while-notify:���������
		notifyAll();
	}
}
