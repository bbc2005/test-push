package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��8�� ����9:49:47 
 */
public class JionDemo {
	public static void main(String[] args) throws InterruptedException {
		JionTest j = new JionTest();
		
		Thread t1 = new Thread(j);
		Thread t2 = new Thread(j);
		
		t1.start();
		
//		t1.join();//t1�߳�Ҫ���������������С���ʱ����һ���߳�����ʱ����ʹ��join������
		
		t2.start();
		
//		t2.setPriority(Thread.MAX_PRIORITY);
		
		for (int i = 0; i < 50; i++) {
//			System.out.println(Thread.currentThread().getName() + "......" + i);
//			System.out.println(Thread.currentThread() + "......" + i);
		}
	}
}
class JionTest implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
//			System.out.println(Thread.currentThread().getName() + "......" + i);
			System.out.println(Thread.currentThread().toString() + "......" + i);
			Thread.yield();//��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
		}
	}
	
}
