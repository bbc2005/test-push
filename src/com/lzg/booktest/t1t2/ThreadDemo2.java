package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��10�� ����10:15:19 
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
		System.out.println("���췽���Ĵ�ӡ��" + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		System.out.println("run�����Ĵ�ӡ��" + Thread.currentThread().getName());
	}
	
	
}
