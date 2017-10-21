package com.lzg.booktest.t1t2;
/** 
 * 线程死锁是一个经典的多线程问题，因为不同的线程都在等待根本不可能被释放的锁，
 * 从而导致所有的任务都无法继续完成。在多线程技术中，死锁是必须避免的，因为这
 * 会造成线程的“假死”。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月17日 下午10:10:25 
 */
public class DeadLockTest {
	/**
	 * 检查是否有死锁：
	 * 先进入JDK安装文件夹的bin目录：
	 * 1：在该目录下打开cmd命令行，输入jsp获得线程id，再输入jstack -l 线程id 查看
	 * 2：在该目录下打开jconsole.exe，选择对应线程连接查看
	 * 3：在该目录下打开jvisualvm.exe，选择对应线程连接查看
	 * @param args
	 * 2017年8月17日 下午10:29:30
	 */
	public static void main(String[] args) {
		try {
			DealThread d = new DealThread();
			d.setFlag("a");
			Thread t1 = new Thread(d);
			t1.start();
			Thread.sleep(100);
			d.setFlag("b");
			Thread t2 = new Thread(d);
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class DealThread implements Runnable{
	public String username;
	public Object lock1 = new Object();
	public Object lock2 = new Object();
	public void setFlag(String username){
		this.username = username;
	}
	@Override
	public void run() {
		if(username.equals("a")){
			synchronized(lock1){
				try {
					System.out.println("username=" + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(lock2){
					System.out.println("按lock1->lock2代码顺序执行了");
				}
			}
		}
		
		if(username.equals("b")){
			synchronized(lock2){
				try {
					System.out.println("username=" + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(lock1){
					System.out.println("按lock2->lock1代码顺序执行了");
				}
			}
		}
	}
	
}