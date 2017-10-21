package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��11�� ����10:23:36 
 */
public class InterruptedDemo3 {

	public static void main(String[] args) {
		InterruptedTest3 t = new InterruptedTest3();
		t.start();
		t.interrupt();
		System.out.println("end!");
	}

}
class InterruptedTest3 extends Thread{

	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 100000; i++) {
				System.out.println("i=" + (i + 1));
			}
			System.out.println("run begin");
			Thread.sleep(200000);//InterruptedException - ����κ��߳��ж��˵�ǰ�̡߳����׳����쳣ʱ����ǰ�̵߳��ж�״̬ �������
			System.out.println("run end");
		} catch (Exception e) {
			System.out.println("��ֹͣ����������sleep������catch��" + Thread.interrupted());

			e.printStackTrace();
		}
	}
	
}
