package com.lzg.booktest.t1t2;
/** 
 * �ػ��̣߳�
 * ��һ��������̣߳����������С���顱�ĺ��壬
 * �������в����ڷ��ػ��߳��ˣ����ػ��߳��Զ����١�
 * ���͵��ػ��gݲ�ݾ������������̡߳�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����4:00:45 
 */
public class DaemonThreadDemo {

	public static void main(String[] args) {
		try {
			DaemonThreadTest t = new DaemonThreadTest();
			t.setDaemon(true);
			t.start();
			Thread.sleep(5000);
			System.out.println("���뿪t����Ҳ���ٴ�ӡ�ˣ�Ҳ����ֹͣ�ˣ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class DaemonThreadTest extends Thread{
	private int i = 0;

	@Override
	public void run() {
		try {
			while (true) {
				i++;
				System.out.println("i=" + i);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}