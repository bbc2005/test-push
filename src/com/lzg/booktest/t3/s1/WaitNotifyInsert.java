package com.lzg.booktest.t3.s1;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月24日 下午4:49:52 
 */
public class WaitNotifyInsert {

	/**
	 * 实战：等待/通知之交叉备份
	 * 创建20个线程，10个将数据备份到A数据库中，
	 * 10将数据备份到B数据库中，并且交叉进行。
	 * @param args
	 * 2017年8月24日 下午4:50:10
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
				System.out.println("★★★★★★★★");
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
				System.out.println("☆☆☆☆☆☆☆☆");
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