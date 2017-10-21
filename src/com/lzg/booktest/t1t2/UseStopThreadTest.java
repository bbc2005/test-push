package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月11日 下午10:48:22 
 * 使用stop()方法停止线程是非常暴力的。
 * 该方法已经被作废，因为如果强制线程停止则有可能使一些清理性的工作
 * 得不到完成；另外一个情况就是对锁定的对象进行了“解锁”，导致数据
 * 得不到同步的处理，出现数据不一致的问题。
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
