package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月10日 下午10:02:53 
 * 执行start()方法的顺序不代表线程启动的顺序
 */
public class ThreadDemo1 {

	public static void main(String[] args) {
		ThreadTestDemo1 t1 = new ThreadTestDemo1(1);
		ThreadTestDemo1 t2 = new ThreadTestDemo1(2);
		ThreadTestDemo1 t3 = new ThreadTestDemo1(3);
		ThreadTestDemo1 t4 = new ThreadTestDemo1(4);
		ThreadTestDemo1 t5 = new ThreadTestDemo1(5);
		ThreadTestDemo1 t6 = new ThreadTestDemo1(6);
		ThreadTestDemo1 t7 = new ThreadTestDemo1(7);
		ThreadTestDemo1 t8 = new ThreadTestDemo1(8);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}

}
class ThreadTestDemo1 extends Thread{
	private int i;
	public ThreadTestDemo1(int i){
		this.i = i;
	}
	@Override
	public void run() {
		System.out.println(i);
	}
	
}

