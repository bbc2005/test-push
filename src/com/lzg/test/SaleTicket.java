package com.lzg.test;
/**
 * ��Ʊʾ��
 * �̹߳�����Դ
 * �̰߳�ȫ��
 * @author DELL
 * �̰߳�ȫ���������ԭ��
 * 1������߳��ڲ������������ 
 * 2�������������ݵ��̴߳����ж���
 * 
 * ��һ���߳���ִ�в����������ݵĶ�����������У������̲߳��������㣬
 * �ͻᵼ���̰߳�ȫ����Ĳ�����
 * 
 * ���˼·��
 * ���ǽ����������������ݵ��̴߳����װ�����������߳���ִ����Щ�����ʱ��
 * �����߳��ǲ����Բ�������ġ�
 * ����Ҫ��ǰ�̰߳���Щ���붼ִ����Ϻ������̲߳ſ��Բ������㡣
 * 
 * ��Java�У���ͬ�������Ϳ��Խ��������⡣
 * 
 * ͬ�������ĸ�ʽ��
 * synchronized(����){
 * 	��Ҫ��ͬ���Ĵ���;
 * }
 * 
 * ͬ���ĺô���������̵߳İ�ȫ���⡣
 * ͬ���ı׶ˣ���Խ�����Ч�ʣ���Ϊͬ������̶߳����ж�ͬ������
 * 
 * ͬ����ǰ�᣺ͬ���б����ж���̲߳�ʹ��ͬһ������
 * 
 *
 */
public class SaleTicket {

	public static void main(String[] args) {
		//��һ�ַ���
		/*Ticket t1=new Ticket();
		Ticket t2=new Ticket();
		Ticket t3=new Ticket();
		Ticket t4=new Ticket();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();*/
		
		//�ڶ��ַ���
		Ticket1 t=new Ticket1();//����һ���߳��������
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		Thread t3=new Thread(t);
		Thread t4=new Thread(t);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		

	}

}
class Ticket extends Thread{
	private static int num=100;//��̬�ǿ��Ե�
	
	public void run(){
		while(true){
			if(num>0){
				System.out.println(Thread.currentThread().getName()+ "......sale......" + num--);
			}
		}
	}
}
class Ticket1 implements Runnable{
	private int num=100;
	Object obj=new Object();

	@Override
	public void run() {
		while(true){
			synchronized (obj) {
				if(num>0){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+ "......sale......" + num--);
				}
			}			
		}
		
	}
	
}
