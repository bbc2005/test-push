package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月7日 下午10:04:34 
 * 
 * 停止线程：
 * 1：stop方法
 * 2：run方法结束。
 * 
 * 怎么控制线程的任务结束呢？
 * 任务中都会有循环结构，只要控制住循环就可以结束任务。
 * 
 * 控制循环通常就用定义标记来完成。
 * 
 * 但是如果线程处于了冻结状态，无法读取标记，如何结束呢？
 * 可以使用interrupt()方法将线程从冻结状态强制恢复到运行状态中来，让线程具备CPU的执行资格。
 * 但是强制动作会发生InterruptedException，记得要处理
 * 
 */
public class StopThreadDemo {
	public static void main(String[] args) {
		StopThread st = new StopThread();
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);
		
		t1.start();
		t2.setDaemon(true);//守护线程
		t2.start();
		
		int num = 1;
		for (; ; ) {
			if(++num==5000){
//				st.setFlag();
				t1.interrupt();
//				t2.interrupt();
				break;
			}
			System.out.println("main......" + num);
		}
		System.out.println("over");
	}
}
class StopThread implements Runnable{
	private boolean flag = true;

	@Override
	public synchronized void run() {
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "......" +e);
				flag=false;
			}
			System.out.println(Thread.currentThread().getName() + "......");
		}
	}
	
	public void setFlag(){
		flag=false;
	}
	
}
