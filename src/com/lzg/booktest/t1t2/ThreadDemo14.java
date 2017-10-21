package com.lzg.booktest.t1t2;
/** 
 * 将任意对象作为对象监视器
 * 多个线程调用同一个对象中的不同名称的synchronized同步方法或
 * synchronized(this)同步代码块时，调用的效果就是按顺序执行，也就是
 * 同步的，阻塞的。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月14日 下午10:40:47 
 */
public class ThreadDemo14 {
	/**
	 * “任意对象”大多数是实例变量及方法的参数，
	 * 使用格式为：synchronized(非this对象x)
	 * @param args
	 * 2017年8月14日 下午10:55:18
	 */
	public static void main(String[] args) {
		ThreadDemo14Service service = new ThreadDemo14Service();
		
		ThreadTest14A a = new ThreadTest14A(service);
		a.setName("A");
		a.start();
		
		ThreadTest14B b = new ThreadTest14B(service);
		b.setName("B");
		b.start();
	}

}
class ThreadDemo14Service{
	private String username;
	private String password;
	private String anyString = new String();
	public void set(String username,String password){
		try {
			synchronized (anyString) {
				System.out.println("线程名称为：" + Thread.currentThread().getName()
						+ "在" + System.currentTimeMillis() + "进入同步块");
				this.username = username;
				Thread.sleep(3000);
				this.password = password;
				System.out.println("线程名称为：" + Thread.currentThread().getName()
						+ "在" + System.currentTimeMillis() + "离开同步块");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest14A extends Thread{
	private ThreadDemo14Service service;
	public ThreadTest14A(ThreadDemo14Service service){
		this.service = service;
	}
	@Override
	public void run() {
		service.set("a", "aa");
	}
}
class ThreadTest14B extends Thread{
	private ThreadDemo14Service service;
	public ThreadTest14B(ThreadDemo14Service service){
		this.service = service;
	}
	@Override
	public void run() {
		service.set("b", "bb");
	}
}