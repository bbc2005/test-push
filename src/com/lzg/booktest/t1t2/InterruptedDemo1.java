package com.lzg.booktest.t1t2;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月11日 下午10:00:33 
 */
public class InterruptedDemo1 {

	public static void main(String[] args) throws InterruptedException {
		InterruptedTest1 t = new InterruptedTest1();
		t.start();
		Thread.sleep(2000);
		t.interrupt();
		System.out.println("end!");
	}

}
class InterruptedTest1 extends Thread{

	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 500000; i++) {
				if (this.interrupted()) {
					System.out.println("已经是停止状态了，我要退出了！");
					//				break;//不能停止线程
					throw new InterruptedException();//用异常结束线程
				}
				System.out.println("i=" + (i + 1));
			}
			System.out.println("在for下面的代码！");
		} catch (Exception e) {
			System.out.println("进入类run方法的catch了！");
			e.printStackTrace();
		}
		System.out.println("我被输出，如果此代码是for又继续运行，线程并未停止！");
	}
	
	
}