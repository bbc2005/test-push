package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月10日 下午10:15:19 
 */
public class ThreadDemo2 {

	public static void main(String[] args) {
		ThreadTestDemo2 t = new ThreadTestDemo2();
//		t.start();
		t.run();
	}

}
class ThreadTestDemo2 extends Thread{

	public ThreadTestDemo2() {
		System.out.println("构造方法的打印：" + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		System.out.println("run方法的打印：" + Thread.currentThread().getName());
	}
	
	
}
