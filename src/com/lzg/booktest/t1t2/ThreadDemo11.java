package com.lzg.booktest.t1t2;
/** 
 * ͬ�������м̳���
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����10:15:53 
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
			System.out.println("int main ��һ�� sleep begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("int main ��һ�� sleep end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadDemo11Sub extends ThreadDemo11Main{

	@Override
//	public void serviceMethod() {//ͬ�����ܼ̳У�
	public synchronized void serviceMethod() {//Ҫͬ����Ҫ������ķ��������synchronized�ؼ���
		try {
			System.out.println("int sub ��һ�� sleep begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("int sub ��һ�� sleep end threadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
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