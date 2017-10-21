package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 下午5:44:50 
 * 脏读
 * dirtyRead
 * 发生脏读的情况是在读取实例变量时，此值已经被其他线程更改过了。
 */
public class ThreadDemo9 {
	/**
	 * 出现脏读是因为public void getValue()方法并不是同步的
	 * @param args
	 * 2017年8月13日 下午5:59:38
	 */
	public static void main(String[] args) {
		try {
			PublicVar publicVar = new PublicVar();
			ThreadTest9 t = new ThreadTest9(publicVar);
			t.start();
			Thread.sleep(200);//打印结果手此值大小影响
			publicVar.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class PublicVar{
	public String username = "A";
	public String password = "AA";
	public synchronized void setValue(String username,String password){
		try {
			this.username = username;
			Thread.sleep(5000);
			this.password = password;
			System.out.println("setValue method thread name="
					+ Thread.currentThread().getName() + " username="
					+ username + " password=" + password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public void getValue(){
	public synchronized void getValue(){//同步
		System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username + " password=" + password);
	}
}
class ThreadTest9 extends Thread{
	private PublicVar publicVar;
	public ThreadTest9(PublicVar publicVar){
		this.publicVar = publicVar;
	}
	@Override
	public void run() {
		publicVar.setValue("B", "BB");
	}
	
}