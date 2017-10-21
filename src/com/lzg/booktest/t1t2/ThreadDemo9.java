package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��13�� ����5:44:50 
 * ���
 * dirtyRead
 * ���������������ڶ�ȡʵ������ʱ����ֵ�Ѿ��������̸߳��Ĺ��ˡ�
 */
public class ThreadDemo9 {
	/**
	 * �����������Ϊpublic void getValue()����������ͬ����
	 * @param args
	 * 2017��8��13�� ����5:59:38
	 */
	public static void main(String[] args) {
		try {
			PublicVar publicVar = new PublicVar();
			ThreadTest9 t = new ThreadTest9(publicVar);
			t.start();
			Thread.sleep(200);//��ӡ����ִ�ֵ��СӰ��
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
	public synchronized void getValue(){//ͬ��
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