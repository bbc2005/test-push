package com.lzg.test;
/**
 * 静态的同步函数使用的锁是：该函数所属字节码文件对象，
 * 可以用getCalss方法获取，也可以用当前类的 类名.class表示。
 * 
 *
 */
public class StaticSynFunctionDemo {
	public static void main(String[] args) {
		Ticket3 t=new Ticket3();//创建一个线程任务对象
		System.out.println("t.getClass():" + t.getClass());
		
		Thread t1=new Thread(t);
		Thread t2=new Thread(t);
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.flag=false;
		t2.start();
	}
}

class Ticket3 implements Runnable{
	private static int num=100;
	Object obj=new Object();
	boolean flag=true;

	@Override
	public void run() {
		System.out.println("this.getClass():" + this.getClass());
		if(flag){
			while(true){
				synchronized (Ticket3.class) {//取到当前字节码对象 也可以这样获得this.getClass()
					if(num>0){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+ "......obj-sale......" + num--);
					}
				}
			}
		}else{
			while(true){
				show();	
			}
		}
	}
	private static synchronized void show(){
		if(num>0){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+ "......function-sale......" + num--);
		}
	}
	
}
