package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����10:29:27 
 * ������interrupt()��return���ʹ����ʵ��ֹͣ�̵߳�Ч����
 */
public class UseReturnInterrupt {

	public static void main(String[] args) {
		try {
			UseReturnTest t = new UseReturnTest();
			t.start();
			Thread.sleep(2000);
			t.interrupt();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
class UseReturnTest extends Thread{

	@Override
	public void run() {
		while(true){
			if(this.isInterrupted()){
				System.out.println("ֹͣ�ˣ�");
				return ;
			}
			System.out.println("timer=" + System.currentTimeMillis());
		}
	}
	
}
