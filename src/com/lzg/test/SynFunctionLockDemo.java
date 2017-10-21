package com.lzg.test;
/**
 * ͬ��������ʹ�õ�����this��
 * 
 * ͬ��������ͬ������������
 * ͬ�����������ǹ̶���this��
 * ͬ���������������Ķ���
 * 
 * �����У�����ʹ��ͬ������顣
 * 
 * ͬ������飬��Ӧ���Ĳ�������ʽ��
 *
 */
public class SynFunctionLockDemo {

	public static void main(String[] args) {
		Ticket2 t=new Ticket2();//����һ���߳��������
		System.out.println("t:" + t);
		
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.flag=false;
		t2.start();
	}

}
class Ticket2 implements Runnable{
	private int num=100;
	Object obj=new Object();
	boolean flag=true;

	@Override
	public void run() {
		System.out.println("this:" + this);
		if(flag){
			while(true){
//				synchronized (obj) {
				synchronized (this) {
					if(num>0){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+ "......obj-sale......" + num--);
					}
				}
			}
		}else{
			while(true){
				show();	
			}
		}
	}
	private synchronized void show(){
		if(num>0){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+ "......function-sale......" + num--);
		}
	}
	
}