package com.lzg.booktest.t3.s1;
/** 
 * 当方法wait()被执行后，锁被自动释放，但执行完notify()方法，
 * 锁却不自动释放。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月22日 下午10:30:55 
 */
public class WaitReleaseLock {
	/**
	 * 方法wait()被执行后，锁被自动释放。
	 * sleep()被执行后，不释放锁。
	 * @param args
	 * 2017年8月22日 下午11:00:47
	 */
	public static void main1(String[] args) {
		Object lock = new Object();
		WrThreadA a = new WrThreadA(lock);
		a.start();
		
		WrThreadB b = new WrThreadB(lock);
		b.start();
	}
	/**
	 * 方法notify()被执行后，不释放锁。
	 * 本例说明：
	 * 必须执行完notify()方法所在的同步synchronized代码块后才释放锁。
	 * @param args
	 * 2017年8月22日 下午10:55:33
	 */
	public static void main(String[] args) {
		Object lock = new Object();
		WrThreadA a = new WrThreadA(lock);
		a.start();
		
		WrThread1 t1 = new WrThread1(lock);
		t1.start();
		
		WrThread2 t2 = new WrThread2(lock);
		t2.start();
	}

}
class WrService{
	public void test(Object lock){
		try {
			synchronized (lock) {
				System.out.println("begin wait()  threadName="
						+ Thread.currentThread().getName());
				lock.wait();//自动释放锁
//				Thread.sleep(40000);//成了同步
				System.out.println("end wait()  threadName="
						+ Thread.currentThread().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void synNotify(Object lock){
		try {
			synchronized (lock) {
				System.out.println("begin notify() threadName="
						+ Thread.currentThread().getName() + "time="
						+ System.currentTimeMillis());
				lock.notify();
				Thread.sleep(5000);
				System.out.println("end notify() threadName="
						+ Thread.currentThread().getName() + "time="
						+ System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class WrThreadA extends Thread{
	private Object lock;

	public WrThreadA(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		WrService service = new WrService();
		service.test(lock);
	}
}
class WrThreadB extends Thread{
	private Object lock;

	public WrThreadB(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		WrService service = new WrService();
		service.test(lock);
	}
}

class WrThread1 extends Thread{
	private Object lock;

	public WrThread1(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		WrService service = new WrService();
		service.synNotify(lock);
	}
	
}

class WrThread2 extends Thread{
	private Object lock;

	public WrThread2(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		WrService service = new WrService();
		service.synNotify(lock);
	}
	
}