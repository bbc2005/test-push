package com.lzg.test;

public class ThreadDemo3 {

	public static void main(String[] args) {
		ThreadTest t1=new ThreadTest("¹þ¹þ");
		ThreadTest t2=new ThreadTest("hh");
		t1.start();
		System.out.println("=============================");
		t2.start();
		System.out.println("---name="+Thread.currentThread().getName());
	}

}
class ThreadTest extends Thread{
	private String name;
	

	public ThreadTest(String name) {
		super(name);
//		this.name = name;
	}


	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 999999999; j++) {
				
			}
//			System.out.println(name+"--->i="+i+"---name="+getName());
			System.out.println(name+"--->i="+i+"---name="+Thread.currentThread().getName());
		}
	}
	
	
}
