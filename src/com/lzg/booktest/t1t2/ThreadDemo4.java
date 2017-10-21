package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月11日 下午10:58:25 
 */
public class ThreadDemo4 {

	public static void main(String[] args) {
		try {
			SynchronizedObject object = new SynchronizedObject();
			ThreadTest4 t = new ThreadTest4(object);
			t.start();
			Thread.sleep(500);
			t.stop();
			System.out.println(object.getUsername() + " "
					+ object.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class SynchronizedObject{
	private String username = "a";
	private String password = "aa";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	synchronized public void pringString(String username,String password){
		try {
			this.username = username;
			Thread.sleep(10000);
			this.password = password;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ThreadTest4 extends Thread{
	private SynchronizedObject object;
	
	public ThreadTest4(SynchronizedObject object){
		this.object = object;
	}

	@Override
	public void run() {
		super.run();
		object.pringString("b", "bb");
	}
	
}