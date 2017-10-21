package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 上午10:44:06 
 * 
 * 死锁：常见情景之一：同步的嵌套
 * 
 */
public class DeadLockDemo {

	public static void main(String[] args) {
		Ticket4 t=new Ticket4();//创建一个线程任务对象
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
class Ticket4 implements Runnable{
	private int num=100;
	Object obj=new Object();
	boolean flag=true;

	@Override
	public void run() {
		System.out.println("this:" + this);
		if(flag){
			while(true){
				synchronized (obj) {
					show();
				}
			}
		}else{
			while(true){
				this.show();	
			}
		}
	}
	private synchronized void show(){//同步函数的锁是固定的this。
		synchronized (obj) {
			if(num>0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+ "......sale......" + num--);
			}
		}
	}
	
}