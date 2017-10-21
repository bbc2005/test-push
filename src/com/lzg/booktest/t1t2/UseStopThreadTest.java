package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��11�� ����10:48:22 
 * ʹ��stop()����ֹͣ�߳��Ƿǳ������ġ�
 * �÷����Ѿ������ϣ���Ϊ���ǿ���߳�ֹͣ���п���ʹһЩ�����ԵĹ���
 * �ò�����ɣ�����һ��������Ƕ������Ķ�������ˡ�����������������
 * �ò���ͬ���Ĵ����������ݲ�һ�µ����⡣
 */
public class UseStopThreadTest {

	public static void main(String[] args) {
		try {
			StopDemo s = new StopDemo();
			s.start();
			Thread.sleep(8000);
			s.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class StopDemo extends Thread{
	private int i = 0;

	@Override
	public void run() {
		super.run();
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
