package com.lzg.test;

public class ThreadDeadLock {
	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();
		
		Thread t1 = new Thread(new SynchThread(obj1, obj2),"t1");
		Thread t2 = new Thread(new SynchThread(obj2, obj3),"t2");
		Thread t3 = new Thread(new SynchThread(obj3, obj1),"t3");
		
		t1.start();
		Thread.sleep(5000);
		t2.start();
		Thread.sleep(5000);
		t3.start();
	}

}

class SynchThread implements Runnable{
	private Object obj1;
	private Object obj2;
	
	public SynchThread(Object obj1, Object obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		System.out.println(name+" acquring lock on " + obj1);
		synchronized (obj1) {
			System.out.println(name + " acquring lock " + obj1);
			work();
			System.out.println(name + " acquring lock on " + obj2);
			synchronized (obj2) {
				System.out.println(name + " acquring lock on " + obj2);
				work();
			}
			System.out.println(name + " released lock on " + obj2);
		}
		System.out.println(name + " released lock on " + obj1);
		System.out.println(name + " finished execution");
	}
	
	private void work(){
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
