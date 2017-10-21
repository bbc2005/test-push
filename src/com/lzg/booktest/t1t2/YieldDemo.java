package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����11:51:33 
 */
public class YieldDemo {

	public static void main(String[] args) {
		YieldTest t = new YieldTest();
		t.start();
	}

}
class YieldTest extends Thread{

	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 50000000; i++) {
			Thread.yield();
			count = count + (i + 1);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("��ʱ��" + (endTime - beginTime) + "���룡");
	}
	
}
