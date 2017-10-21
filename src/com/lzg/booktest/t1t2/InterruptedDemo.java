package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��10�� ����10:53:24 
 */
public class InterruptedDemo {

	public static void main(String[] args) {
		InterruptedTest t = new InterruptedTest();
		t.start();
		try {
			Thread.sleep(1000);
//			t.interrupt();
			Thread.currentThread().interrupt();
//			System.out.println(".......................�Ƿ�ֹͣ1��=" + t.interrupted());
//			System.out.println(".......................�Ƿ�ֹͣ2��=" + t.interrupted());
			System.out.println(".......................�Ƿ�ֹͣ1��=" + Thread.interrupted());
			System.out.println(".......................�Ƿ�ֹͣ2��=" + Thread.interrupted());
			System.out.println(".......................�Ƿ�ֹͣ3��=" + Thread.interrupted());
//			System.out.println(".......................�Ƿ�ֹͣ1��=" + t.isInterrupted());
//			System.out.println(".......................�Ƿ�ֹͣ2��=" + t.isInterrupted());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end!");
	}

}
class InterruptedTest extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < 500000; i++) {
			System.out.println("i=" + (i + 1));
		}
	}
	
}
