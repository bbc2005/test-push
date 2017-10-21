package com.lzg.booktest.t1t2;
/** 
 * synchronized���������ı׶�
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��14�� ����8:59:42 
 */
public class ThreadDemo12 {

	public static void main(String[] args) {
		ThreadDemo12Task task = new ThreadDemo12Task();
		ThreadTest12A a = new ThreadTest12A(task);
		a.start();
		
		ThreadTest12B b = new ThreadTest12B(task);
		b.start();
		
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long beginTime = CommonUtil.beginTime1;
		if(CommonUtil.beginTime2 < CommonUtil.beginTime1){
			beginTime = CommonUtil.beginTime2;
		}
		
		long endTime = CommonUtil.endTime1;
		if(CommonUtil.endTime2 > CommonUtil.endTime1){
			endTime = CommonUtil.endTime2;
		}
		
		System.out.println("��ʱ��" + ((endTime - beginTime) / 1000));
	}

}
class ThreadDemo12Task{
	private String getData1;
	private String getData2;
	/**
	 * һ��ִ�г�ʱ�������
	 * 
	 * 2017��8��14�� ����9:17:02
	 */
	public synchronized void doLongTimeTask(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "��ʱ�䴦��������Զ�̷��ص�ֵ1 threadName="
					+ Thread.currentThread().getName();
			getData2 = "��ʱ�䴦��������Զ�̷��ص�ֵ2 threadName="
					+ Thread.currentThread().getName();
			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ͬ���������ͬ�������ı׶�
	 * 
	 * 2017��8��14�� ����9:46:11
	 */
	public void doLongTimeTask1(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			String privateGetData1 = "��ʱ�䴦��������Զ�̷��ص�ֵ1 threadName="
					+ Thread.currentThread().getName();
			String privateGetData2 = "��ʱ�䴦��������Զ�̷��ص�ֵ2 threadName="
					+ Thread.currentThread().getName();
			synchronized(this){
				getData1 = privateGetData1;
				getData2 = privateGetData2;
			}
			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class CommonUtil{
	public static long beginTime1;
	public static long endTime1;
	public static long beginTime2;
	public static long endTime2;
}
class ThreadTest12A extends Thread{
	private ThreadDemo12Task task;
	public ThreadTest12A(ThreadDemo12Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		CommonUtil.beginTime1 = System.currentTimeMillis();
//		task.doLongTimeTask();
		task.doLongTimeTask1();
		CommonUtil.endTime1 = System.currentTimeMillis();
	}
	
}
class ThreadTest12B extends Thread{
	private ThreadDemo12Task task;
	public ThreadTest12B(ThreadDemo12Task task){
		this.task = task;
	}
	@Override
	public void run() {
		super.run();
		CommonUtil.beginTime2 = System.currentTimeMillis();
//		task.doLongTimeTask();
		task.doLongTimeTask1();
		CommonUtil.endTime2 = System.currentTimeMillis();
	}
	
}