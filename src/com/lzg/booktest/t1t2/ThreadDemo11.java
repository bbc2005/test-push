package com.lzg.booktest.t1t2;
/** 
 * 同步不具有继承性
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午10:15:53 
 */
public class ThreadDemo11 {

	public static void main(String[] args) {
		ThreadDemo11Sub sub = new ThreadDemo11Sub();
		ThreadTest11A a = new ThreadTest11A(sub);
		a.setName("A");
		a.start();
		
		ThreadTest11B b = new ThreadTest11B(sub);
		b.setName("B");
		b.start();
	}

}
class ThreadDemo11Main{
	public synchronized void serviceMethod(){
		try {
			System.out.println("int main 下一步 sleep begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("int main 下一步 sleep end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadDemo11Sub extends ThreadDemo11Main{

	@Override
//	public void serviceMethod() {//同步不能继承，
	public synchronized void serviceMethod() {//要同步还要在子类的方法中添加synchronized关键字
		try {
			System.out.println("int sub 下一步 sleep begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("int sub 下一步 sleep end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
			super.serviceMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
class ThreadTest11A extends Thread{
	private ThreadDemo11Sub sub;
	public ThreadTest11A(ThreadDemo11Sub sub){
		this.sub = sub;
	}
	@Override
	public void run() {
		sub.serviceMethod();
	}
}
class ThreadTest11B extends Thread{
	private ThreadDemo11Sub sub;
	public ThreadTest11B(ThreadDemo11Sub sub){
		this.sub = sub;
	}
	@Override
	public void run() {
		sub.serviceMethod();
	}
}