package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午9:26:20 
 */
public class ThreadDemo10 {
	/**
	 * 出现异常，锁自动释放
	 * 当一个线程执行的代码出现异常时，其所持有的锁会自动释放。
	 * @param args
	 * 2017年8月13日 下午9:46:46
	 */
	public static void main(String[] args) {
		try {
			ThreadDemo10Service service = new ThreadDemo10Service();
			ThreadTest10A a = new ThreadTest10A(service);
			a.setName("a");
			a.start();
			Thread.sleep(500);
			ThreadTest10B b = new ThreadTest10B(service);
			b.setName("b");
			b.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class ThreadDemo10Service{
	public synchronized void testMethod(){
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("ThreadName=" + Thread.currentThread().getName() + " run beginTime=" + System.currentTimeMillis());
			int i = 1;
			while(i == 1){
				String r = ("" + Math.random());
//				System.out.println(r);
				if(r.substring(0, 8).equals("0.123456")){
					System.out.println("ThreadName=" + Thread.currentThread().getName() + " run exceptionTime=" + System.currentTimeMillis());
					Integer.parseInt("a");
				}
			}
		}else{
			System.out.println("Thread B run Time=" + System.currentTimeMillis());
		}
	}
}
class ThreadTest10A extends Thread{
	private ThreadDemo10Service service;
	public ThreadTest10A(ThreadDemo10Service service){
		this.service = service;
	}

	@Override
	public void run() {
		service.testMethod();
	}
	
}
class ThreadTest10B extends Thread{
	private ThreadDemo10Service service;
	public ThreadTest10B(ThreadDemo10Service service){
		this.service = service;
	}

	@Override
	public void run() {
		service.testMethod();
	}
	
}