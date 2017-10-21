package com.lzg.test;
/**
 * 同步函数的使用的锁是this；
 * 
 * 同步函数和同步代码块的区别：
 * 同步函数的锁是固定的this。
 * 同步代码的锁是任意的对象。
 * 
 * 开发中：建议使用同步代码块。
 * 
 * 同步代码块，对应锁的操作是隐式的
 *
 */
public class SynFunctionLockDemo {

	public static void main(String[] args) {
		Ticket2 t=new Ticket2();//创建一个线程任务对象
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