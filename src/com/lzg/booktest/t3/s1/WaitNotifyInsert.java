package com.lzg.booktest.t3.s1;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��24�� ����4:49:52 
 */
public class WaitNotifyInsert {

	/**
	 * ʵս���ȴ�/֪֮ͨ���汸��
	 * ����20���̣߳�10�������ݱ��ݵ�A���ݿ��У�
	 * 10�����ݱ��ݵ�B���ݿ��У����ҽ�����С�
	 * @param args
	 * 2017��8��24�� ����4:50:10
	 */
	public static void main(String[] args) {
		DBTools dbTools = new DBTools();
		for (int i = 0; i < 10; i++) {
			BackupB output = new BackupB(dbTools);
			output.start();
			
			BackupA input = new BackupA(dbTools);
			input.start();
		}
	}

}
class DBTools{
	private volatile boolean prevIsA = false;
	public synchronized void backupA(){
		try {
			while (prevIsA == true) {
				wait();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("���������");
			}
			prevIsA = true;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void backupB(){
		try {
			while (prevIsA == false) {
				wait();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("���������");
			}
			prevIsA = false;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class BackupA extends Thread{
	private DBTools dbTools;

	public BackupA(DBTools dbTools) {
		super();
		this.dbTools = dbTools;
	}

	@Override
	public void run() {
		dbTools.backupA();
	}
}

class BackupB extends Thread{
	private DBTools dbTools;

	public BackupB(DBTools dbTools) {
		super();
		this.dbTools = dbTools;
	}

	@Override
	public void run() {
		dbTools.backupB();
	}
	
}