package com.lzg.booktest.t1t2;
/** 
 * �߳�������һ������Ķ��߳����⣬��Ϊ��ͬ���̶߳��ڵȴ����������ܱ��ͷŵ�����
 * �Ӷ��������е������޷�������ɡ��ڶ��̼߳����У������Ǳ������ģ���Ϊ��
 * ������̵߳ġ���������
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��17�� ����10:10:25 
 */
public class DeadLockTest {
	/**
	 * ����Ƿ���������
	 * �Ƚ���JDK��װ�ļ��е�binĿ¼��
	 * 1���ڸ�Ŀ¼�´�cmd�����У�����jsp����߳�id��������jstack -l �߳�id �鿴
	 * 2���ڸ�Ŀ¼�´�jconsole.exe��ѡ���Ӧ�߳����Ӳ鿴
	 * 3���ڸ�Ŀ¼�´�jvisualvm.exe��ѡ���Ӧ�߳����Ӳ鿴
	 * @param args
	 * 2017��8��17�� ����10:29:30
	 */
	public static void main(String[] args) {
		try {
			DealThread d = new DealThread();
			d.setFlag("a");
			Thread t1 = new Thread(d);
			t1.start();
			Thread.sleep(100);
			d.setFlag("b");
			Thread t2 = new Thread(d);
			t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class DealThread implements Runnable{
	public String username;
	public Object lock1 = new Object();
	public Object lock2 = new Object();
	public void setFlag(String username){
		this.username = username;
	}
	@Override
	public void run() {
		if(username.equals("a")){
			synchronized(lock1){
				try {
					System.out.println("username=" + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(lock2){
					System.out.println("��lock1->lock2����˳��ִ����");
				}
			}
		}
		
		if(username.equals("b")){
			synchronized(lock2){
				try {
					System.out.println("username=" + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(lock1){
					System.out.println("��lock2->lock1����˳��ִ����");
				}
			}
		}
	}
	
}