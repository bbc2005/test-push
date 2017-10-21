package com.lzg.booktest.t3.s2;
/** 
 * ����join��ʹ��
 * 
 * �ںܶ�����£����̴߳������������̣߳�������߳���
 * Ҫ���д����ĺ�ʱ���㣬���߳��������������߳̽���֮ǰ������
 * ��ʱ��������߳���ȴ����߳�ִ�����֮���ٽ������������߳�
 * ����һ�����ݣ����߳�Ҫȡ����������е�ֵ����Ҫ�õ�join()
 * �����ˡ�����join()�������ǵȴ��̶߳������١�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��25�� ����9:56:07 
 */
public class JoinDemo1 {

	/**
	 * ʹ��joinǰ���̵�
	 * @param args
	 * 2017��8��25�� ����10:03:41
	 */
	public static void main1(String[] args) {
		JoinThread1 t = new JoinThread1();
		t.start();
//		Thread.sleep(?);
		System.out.println("���뵱t����ִ����Ϻ���ִ��");
		System.out.println("����������е�sleep()�е�ֵӦ���Ƕ����أ�");
		System.out.println("���ǣ�����ȷ����");
	}
	/**
	 * ��join()�����������
	 * join��������ʹ�������̶߳���x����ִ��run()�����е�����
	 * ���ǵ�ǰ�߳�z���������ڵ��������ȴ��߳�x���ٺ��ټ���ִ��
	 * �߳�z����Ĵ��롣
	 * join����ʹ�߳��Ŷ����е����á�join���ڲ�ʹ��wait()��������
	 * �ȴ�����synchronizedʹ�õ��ǡ������������ԭ����Ϊͬ����
	 * @param args
	 * 2017��8��25�� ����10:04:36
	 */
	public static void main2(String[] args) {
		try {
			JoinThread2 t = new JoinThread2();
			t.start();
			t.join();
			System.out.println("���뵱t����ִ����Ϻ�����ִ�У��������ˣ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join���쳣
	 * ��join�����У������ǰ�̶߳����жϣ���ǰ�̳߳����쳣��
	 * �������쳣��a���ڼ������У���δ�����쳣��������ִ�е�״̬��
	 * @param args
	 * 2017��8��25�� ����10:12:02
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
	 * join(long)��ʹ��
	 * @param args
	 * 2017��8��25�� ����10:28:45
	 */
	public static void main4(String[] args) {
		try {
			JoinLongThread t = new JoinLongThread();
			t.start();
			t.join(2000);//ֻ��2��
//			Thread.sleep(2000);
			System.out.println("  end time=" + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * join(long)��sleep(long)������
	 * join(long)�Ĺ������ڲ���ʹ��wait(long)������ʵ�ֵģ�
	 * ����join(long)���������ͷ������ص㡣
	 * sleep(long)����ȴ���ͷ�����
	 */
	
	/**
	 * sleep(long)���ͷ���
	 * @param args
	 * 2017��8��25�� ����10:36:31
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
	 * join(long)�ͷ�����
	 * @param args
	 * 2017��8��25�� ����10:56:20
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
	 * join()����Ĵ�����ǰ���У���������
	 * @param args
	 * 2017��8��26�� ����10:32:59
	 */
	public static void main(String[] args) {
		try {
			JoinMore1ThreadB b = new JoinMore1ThreadB();
			JoinMore1ThreadA a = new JoinMore1ThreadA(b);
			a.start();
			b.start();
			b.join(2000);//û�����main end����һֱ�ȴ�ӡ����
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
			System.out.println("�߳�B��run end ����ӡ��");
		} catch (Exception e) {
			System.out.println("�߳�B��catch����ӡ��");
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
				//			Thread.sleep();//���ͷ�����
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
		System.out.println("��ӡ�� bSservice time=" + System.currentTimeMillis());
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
				b.join();//˵��join�ͷ�����
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