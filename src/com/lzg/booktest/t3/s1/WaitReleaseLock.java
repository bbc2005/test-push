package com.lzg.booktest.t3.s1;
/** 
 * ������wait()��ִ�к������Զ��ͷţ���ִ����notify()������
 * ��ȴ���Զ��ͷš�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��22�� ����10:30:55 
 */
public class WaitReleaseLock {
	/**
	 * ����wait()��ִ�к������Զ��ͷš�
	 * sleep()��ִ�к󣬲��ͷ�����
	 * @param args
	 * 2017��8��22�� ����11:00:47
	 */
	public static void main1(String[] args) {
		Object lock = new Object();
		WrThreadA a = new WrThreadA(lock);
		a.start();
		
		WrThreadB b = new WrThreadB(lock);
		b.start();
	}
	/**
	 * ����notify()��ִ�к󣬲��ͷ�����
	 * ����˵����
	 * ����ִ����notify()�������ڵ�ͬ��synchronized��������ͷ�����
	 * @param args
	 * 2017��8��22�� ����10:55:33
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
				lock.wait();//�Զ��ͷ���
//				Thread.sleep(40000);//����ͬ��
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