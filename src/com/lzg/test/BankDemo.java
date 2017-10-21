package com.lzg.test;
/**
 * 
 * 需求：储户，两个，每个都到银行存钱每次存100，共存3次
 *
 */
public class BankDemo {

	public static void main(String[] args) {
		Cus c = new Cus();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		t1.start();
		t2.start();
	}

}
class Bank{
	private int sum;
	private Object obj = new Object();
	public synchronized void add(int num){//同步函数
//		synchronized (obj) {//同步代码块
			sum=sum+ num;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "......sum=" + sum);
//		}
	}
}

class Cus implements Runnable{
	private Bank b=new Bank();
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			b.add(100);
		}
		
	}
	
}
