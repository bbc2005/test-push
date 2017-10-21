package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��11�� ����10:00:33 
 */
public class InterruptedDemo1 {

	public static void main(String[] args) throws InterruptedException {
		InterruptedTest1 t = new InterruptedTest1();
		t.start();
		Thread.sleep(2000);
		t.interrupt();
		System.out.println("end!");
	}

}
class InterruptedTest1 extends Thread{

	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 500000; i++) {
				if (this.interrupted()) {
					System.out.println("�Ѿ���ֹͣ״̬�ˣ���Ҫ�˳��ˣ�");
					//				break;//����ֹͣ�߳�
					throw new InterruptedException();//���쳣�����߳�
				}
				System.out.println("i=" + (i + 1));
			}
			System.out.println("��for����Ĵ��룡");
		} catch (Exception e) {
			System.out.println("������run������catch�ˣ�");
			e.printStackTrace();
		}
		System.out.println("�ұ����������˴�����for�ּ������У��̲߳�δֹͣ��");
	}
	
	
}