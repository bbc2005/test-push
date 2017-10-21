package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月13日 上午10:29:27 
 * 将方法interrupt()与return结合使用能实现停止线程的效果！
 */
public class UseReturnInterrupt {

	public static void main(String[] args) {
		try {
			UseReturnTest t = new UseReturnTest();
			t.start();
			Thread.sleep(2000);
			t.interrupt();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
class UseReturnTest extends Thread{

	@Override
	public void run() {
		while(true){
			if(this.isInterrupted()){
				System.out.println("停止了！");
				return ;
			}
			System.out.println("timer=" + System.currentTimeMillis());
		}
	}
	
}
