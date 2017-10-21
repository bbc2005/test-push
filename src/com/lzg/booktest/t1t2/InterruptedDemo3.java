package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月11日 下午10:23:36 
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
			Thread.sleep(200000);//InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			System.out.println("run end");
		} catch (Exception e) {
			System.out.println("先停止，再遇到了sleep！进入catch！" + Thread.interrupted());

			e.printStackTrace();
		}
	}
	
}
