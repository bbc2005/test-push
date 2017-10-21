package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��11�� ����10:14:20 
 * �ڳ�˯��ֹͣ�߳�
 */
public class InterruptedDemo2 {

	public static void main(String[] args) {
		try {
			InterruptedTest2 t = new InterruptedTest2();
			t.start();
			Thread.sleep(200);
			t.interrupt();
		} catch (Exception e) {
			System.out.println("main catch");
		}
		System.out.println("end!");
	}

}
class InterruptedTest2 extends Thread{

	@Override
	public void run() {
		super.run();
		try {
			System.out.println("run begin");
			Thread.sleep(200000);
			System.out.println("run end");
		} catch (Exception e) {
			System.out.println("�ڳ�˯�б�ֹͣ������catch��" + this.isInterrupted());
			e.printStackTrace();
		}
	}
	
}