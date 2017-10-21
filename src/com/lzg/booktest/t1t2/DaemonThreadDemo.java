package com.lzg.booktest.t1t2;
/** 
 * 守护线程：
 * 是一种特殊的线程，它的特性有“陪伴”的含义，
 * 当进程中不存在非守护线程了，则守护线程自动销毁。
 * 典型的守护豨莶草就是垃圾回收线程。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午4:00:45 
 */
public class DaemonThreadDemo {

	public static void main(String[] args) {
		try {
			DaemonThreadTest t = new DaemonThreadTest();
			t.setDaemon(true);
			t.start();
			Thread.sleep(5000);
			System.out.println("我离开t对象也不再打印了，也就是停止了！");
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