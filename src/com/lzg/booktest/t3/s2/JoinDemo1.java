package com.lzg.booktest.t3.s2;
/** 
 * 方法join的使用
 * 
 * 在很多情况下，主线程创建并启动子线程，如果子线程中
 * 要进行大量的耗时运算，主线程往往将早于子线程结束之前结束。
 * 这时，如果主线程想等待子线程执行完成之后再结束，比如子线程
 * 处理一个数据，主线程要取得这个数据中的值，就要用到join()
 * 方法了。方法join()的作用是等待线程对象销毁。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月25日 下午9:56:07 
 */
public class JoinDemo1 {

	/**
	 * 使用join前的铺垫
	 * @param args
	 * 2017年8月25日 下午10:03:41
	 */
	public static void main1(String[] args) {
		JoinThread1 t = new JoinThread1();
		t.start();
//		Thread.sleep(?);
		System.out.println("我想当t对象执行完毕后再执行");
		System.out.println("但上面代码中的sleep()中的值应该是多少呢？");
		System.out.println("答案是：不能确定！");
	}
	/**
	 * 用join()解决上面问题
	 * join的作用是使所属的线程对象x正常执行run()方法中的任务，
	 * 而是当前线程z进行无限期的阻塞，等待线程x销毁后再继续执行
	 * 线程z后面的代码。
	 * join具有使线程排队运行的作用。join在内部使用wait()方法进行
	 * 等待，而synchronized使用的是“对象监视器”原理作为同步。
	 * @param args
	 * 2017年8月25日 下午10:04:36
	 */
	public static void main2(String[] args) {
		try {
			JoinThread2 t = new JoinThread2();
			t.start();
			t.join();
			System.out.println("我想当t对象执行完毕后我再执行，我做到了！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join与异常
	 * 在join过程中，如果当前线程对象被中断，则当前线程出现异常。
	 * 出现了异常：a还在继续运行，并未出现异常，是正常执行的状态。
	 * @param args
	 * 2017年8月25日 下午10:12:02
	 */
	public static void main3(String[] args) {
		try {
			JoinExceptionThreadB b = new JoinExceptionThreadB();
			b.start();
			Thread.sleep(500);
			JoinExceptionThreadC c = new JoinExceptionThreadC(b);
			c.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join(long)的使用
	 * @param args
	 * 2017年8月25日 下午10:28:45
	 */
	public static void main4(String[] args) {
		try {
			JoinLongThread t = new JoinLongThread();
			t.start();
			t.join(2000);//只等2秒
//			Thread.sleep(2000);
			System.out.println("  end time=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join(long)与sleep(long)的区别
	 * join(long)的功能在内部是使用wait(long)方法来实现的，
	 * 所以join(long)方法具有释放锁的特点。
	 * sleep(long)方法却不释放锁。
	 */
	
	/**
	 * sleep(long)不释放锁
	 * @param args
	 * 2017年8月25日 下午10:36:31
	 */
	public static void main5(String[] args) {
		try {
			JoinSleepThreadB b = new JoinSleepThreadB();
			JoinSleepThreadA a = new JoinSleepThreadA(b);
			a.start();
			Thread.sleep(1000);
			JoinSleepThreadC c = new JoinSleepThreadC(b);
			c.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join(long)释放锁：
	 * @param args
	 * 2017年8月25日 下午10:56:20
	 */
	public static void main6(String[] args) {
		try {
			JoinSleepThreadB b = new JoinSleepThreadB();
			JoinSleepThreadD d = new JoinSleepThreadD(b);
			d.start();
			Thread.sleep(1000);
			JoinSleepThreadC c = new JoinSleepThreadC(b);
			c.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join()后面的代码提前运行：出现意外
	 * @param args
	 * 2017年8月26日 上午10:32:59
	 */
	public static void main(String[] args) {
		try {
			JoinMore1ThreadB b = new JoinMore1ThreadB();
			JoinMore1ThreadA a = new JoinMore1ThreadA(b);
			a.start();
			b.start();
			b.join(2000);//没有这个main end几乎一直先打印出来
			System.out.println("         main end "
					+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class JoinThread1 extends Thread{
	@Override
	public void run() {
		try {
			int secondValue = (int) (Math.random() * 10000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class JoinThread2 extends Thread{
	@Override
	public void run() {
		try {
			int secondValue = (int) (Math.random() * 10000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class JoinExceptionThreadA extends Thread{

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String newString = new String();
			Math.random();
		}
	}
	
}
class JoinExceptionThreadB extends Thread{

	@Override
	public void run() {
		try {
			JoinExceptionThreadA a = new JoinExceptionThreadA();
			a.start();
			a.join();
			System.out.println("线程B在run end 处打印了");
		} catch (Exception e) {
			System.out.println("线程B在catch处打印了");
			e.printStackTrace();
		}
	}
	
}
class JoinExceptionThreadC extends Thread{
	private JoinExceptionThreadB b;

	public JoinExceptionThreadC(JoinExceptionThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		b.interrupt();
	}
	
}
class JoinLongThread extends Thread{

	@Override
	public void run() {
		try {
			System.out.println("begin time=" + System.currentTimeMillis());
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
class JoinSleepThreadA extends Thread{
	private JoinSleepThreadB b;

	public JoinSleepThreadA(JoinSleepThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				b.start();
				Thread.sleep(6000);
				//			Thread.sleep();//不释放锁！
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
class JoinSleepThreadB extends Thread{

	@Override
	public void run() {
		try {
			System.out.println(" b run begin time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println(" b run   end time="
					+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void bService(){
		System.out.println("打印了 bSservice time=" + System.currentTimeMillis());
	}
	
}

class JoinSleepThreadC extends Thread{
	private JoinSleepThreadB b;

	public JoinSleepThreadC(JoinSleepThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		b.bService();
	}
	
}

class JoinSleepThreadD extends Thread{
	private JoinSleepThreadB b;

	public JoinSleepThreadD(JoinSleepThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				b.start();
				b.join();//说明join释放锁了
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					String newString = new String();
					Math.random();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class JoinMore1ThreadA extends Thread{
	private JoinMore1ThreadB b;

	public JoinMore1ThreadA(JoinMore1ThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				System.out.println("begin A threadName="
						+ Thread.currentThread().getName() + "  "
						+ System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println("  end A threadNmae="
						+ Thread.currentThread().getName() + "  "
						+ System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class JoinMore1ThreadB extends Thread{

	@Override
	public synchronized void run() {
		try {
			System.out.println("begin B threadName="
					+ Thread.currentThread().getName() + "  "
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("  end B threadName="
					+ Thread.currentThread().getName() + "  "
					+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}