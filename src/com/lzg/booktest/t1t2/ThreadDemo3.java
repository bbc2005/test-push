package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月10日 下午10:27:57 
 */
public class ThreadDemo3 {

	public static void main(String[] args) {
		CountOperate c = new CountOperate();
		Thread t = new Thread(c);
		System.out.println("main begin t isAlive=" + t.isAlive());
		t.setName("A");
		t.start();
		System.out.println("main end t isAlive=" + t.isAlive());
	}

}
class CountOperate extends Thread{
	public CountOperate(){
		System.out.println("CountOperate---begin");
		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("CountOperate---end");
	}

	@Override
	public void run() {
		System.out.println("run---begin");
		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("run---end");
	}
	
	
}
