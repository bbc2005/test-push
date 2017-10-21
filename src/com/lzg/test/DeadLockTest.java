package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 上午10:57:05 
 */
public class DeadLockTest {
	public static void main(String[] args) {
		DeadLock a=new DeadLock(true);
		DeadLock b=new DeadLock(false);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		
		t1.start();
		t2.start();

	}
}
class DeadLock implements Runnable{
	private boolean flag;
	DeadLock(boolean flag){
		this.flag=flag;
	}
	@Override
	public void run() {
		if(flag){
			while(true){
				synchronized (MyLock.locka) {
					System.out.println(Thread.currentThread().getName()+"......if locka......");
					synchronized (MyLock.lockb) {
						System.out.println(Thread.currentThread().getName()+"......if lockb......");
					}
				}
			}
		}else{
			while(true){
				synchronized (MyLock.lockb) {
					System.out.println(Thread.currentThread().getName()+"......else lockb......");
					synchronized (MyLock.locka) {
						System.out.println(Thread.currentThread().getName()+"......else locka......");
					}
				}
			}
		}
	}
}
class MyLock{
	public static final Object locka=new Object();
	public static final Object lockb=new Object();
}