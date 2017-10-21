package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月11日 下午10:14:20 
 * 在沉睡中停止线程
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
			System.out.println("在沉睡中被停止！进入catch！" + this.isInterrupted());
			e.printStackTrace();
		}
	}
	
}