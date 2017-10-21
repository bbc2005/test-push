package com.lzg.booktest.t1t2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/** 
 * �ؼ���volatile����Ҫ������ʹ�����ڶ���̼߳�ɼ���
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��19�� ����4:52:11 
 * 
 * ʹ��volatile�ؼ���������ʵ�������ڶ���߳�֮��Ŀɼ��ԣ���
 * volatile�ؼ�����������ȱ���ǲ�֧��ԭ���ԡ�
 * 
 * �ؼ���synchronized��volatile������
 * 1��volatile���߳�ͬ����������ʵ�֣�����volatile���ܿ϶���synchronizedҪ�ã�
 * ����volatileֻ�������ڱ�������synchronized�������η������Լ�����顣����JDK��
 * �汾�ķ�����synchronized�ؼ�����ִ��Ч���ϵõ��ܴ��������ڿ�����ʹ��synchronized
 * �ؼ��ֵı��ʻ��ǱȽϴ�ġ�
 * 2�����̷߳���volatile���ᷢ����������synchronized�����������
 * 3��volatile�ܱ�֤���ݵĿɼ��ԣ������ܱ�֤ԭ���ԣ���synchronized
 * ���Ա�֤ԭ���ԣ�Ҳ���Լ�ӱ�֤�ɼ��ԣ���Ϊ���Ὣ˽���ڴ�͹����ڴ��е�������ͬ����
 * 4��volatile������Ǳ����ڶ���߳�֮��Ŀɼ��ԣ���synchronized������Ƕ��
 * �߳�֮�������Դ��ͬ���ԡ�
 * 
 * �̰߳�ȫ����ԭ���ԺͿɼ����������棬Java��ͬ�����ƶ���Χ��������������ȷ���߳�
 * ��ȫ�ġ�
 * 
 * volatile��Ҫʹ�õĳ������ڶ���߳��п��Ը�֪ʵ�������������ˣ����ҿ��Ի��
 * ���µ�ֵʹ�ã�Ҳ�����ö��̶߳�ȡ�������ʱ���Ի������ֵʹ�á�
 * volatile��ʾ�߳�ÿ�δӹ����ڴ��ж�ȡ�����������Ǵ�˽���ڴ��ж�ȡ��������
 * ��֤��ͬ�����ݵĿɼ��ԡ�
 * 
 * synchronized���Ա�֤��ͬһʱ�̣�ֻ��һ���߳̿���ִ��ĳһ��������ĳһ��
 * ����顣���������������������ԺͿɼ��ԡ�ͬ��synchronized�������Խ��һ��
 * �߳̿��������ڲ�һ�µ�״̬�������Ա�֤����ͬ����������ͬ��������ÿ���߳�
 * ����������ͬһ��������֮ǰ���е��޸�Ч����
 * ѧϰ���̲߳�����Ҫ���ء��������⣬���޿ɼ�����
 */
public class VolatileThreadDemo {
	/**
	 * ͣ��������ԭ���ǣ�main�߳�һֱ�ڴ���while()ѭ��������
	 * �����ܼ���ִ�к���Ĵ��롣
	 * ����취��ʹ�ö��̼߳���
	 * @param args
	 * 2017��8��19�� ����5:18:34
	 */
	public static void main1(String[] args) {
		VtdPrintString vps = new VtdPrintString();
		vps.printStringMethod();
		System.out.println("��Ҫֹͣ���� stopThread=" + Thread.currentThread().getName());
		vps.setContinuePrint(false);
	}
	/**
	 * ���ͬ����ѭ��
	 * �ڲ���JVM�ϻ������ѭ��
	 * ����취��ʹ��volatile�ؼ���
	 * @param args
	 * 2017��8��19�� ����5:24:04
	 */
	public static void main2(String[] args) {
		VtdPrintString1 vps = new VtdPrintString1();
		new Thread(vps).start();
		System.out.println("��Ҫֹͣ���� stopThread=" + Thread.currentThread().getName());
		vps.setContinuePrint(false);
	}
	
	/**
	 * ����첽��ѭ��
	 * �ؼ���volatile��������ǿ�ƴӹ�����ջ��ȡ�ñ�����ֵ��������
	 * ���߳�˽������ջ��ȡ�ñ�����ֵ��
	 */
	
	/**
	 * ��JVM����Ϊ -serverʱ������ѭ��
	 * ������VtdRunThread �߳�ʱ������private boolean isRunning =true;������
	 * ������ջ���̵߳�˽�ж�ջ�С���JVM������Ϊ-serverģʽʱΪ���̵߳����е�Ч�ʣ��߳�
	 * һֱ��˽�ж�ջ��ȡ��isRunning��ֵ��true��������t.setRunning(false);��Ȼ��
	 * ִ�У����µ�ȴ�ǹ�����ջ�е�isRunning����ֵfalse,����һֱ������ѭ����״̬��
	 * @param args
	 * 2017��8��19�� ����5:26:31
	 * @throws InterruptedException 
	 */
	public static void main3(String[] args) throws InterruptedException {
		VtdRunThread t = new VtdRunThread();
		t.start();
		Thread.sleep(1000);
		t.setRunning(false);
		System.out.println("�Ѿ���ֵΪfalse");
	}
	/**
	 * volatile��Ȼ������ʵ�������ڶ���߳�֮��Ŀɼ��ԣ�����ȴ���߱�
	 * ͬ���ԣ���ôҲ�Ͳ��߱�ԭ���ԡ�
	 * @param args
	 * 2017��8��20�� ����11:46:49
	 */
	public static void main4(String[] args) {
		VolatileTestThread[] vttArray = new VolatileTestThread[100];
		for (int i = 0; i < vttArray.length; i++) {
			vttArray[i] = new VolatileTestThread();
			vttArray[i].setName("a" + i);
		}
		for (int i = 0; i < vttArray.length; i++) {
			vttArray[i].start();
		}
	}
	/**
	 * ʹ��ԭ�������i++����
	 * 
	 * ԭ�Ӳ����ǲ��ָܷ�����壬û�������߳��ܹ��жϻ�������ԭ�Ӳ�����
	 * ������һ��ԭ�ӣ�atomic�����;���һ��ԭ�Ӳ������õ����ͣ���������
	 * û����������������̰߳�ȫ��thread-safe����
	 * @param args
	 * 2017��8��20�� ����3:26:12
	 */
	public static void main5(String[] args) {
		
		AtomicIntegerTest t = new AtomicIntegerTest();
		
		Thread t1 = new Thread(t);
		t1.setName("t1");
		t1.start();
		Thread t2 = new Thread(t);
		t2.setName("t2");
		t2.start();
		Thread t3 = new Thread(t);
		t3.setName("t3");
		t3.start();
		Thread t4 = new Thread(t);
		t4.setName("t4");
		t4.start();
		Thread t5 = new Thread(t);
		t5.setName("t5");
		t5.start();
		
	}
	/**
	 * ԭ����Ҳ������ȫ��ȫ
	 * 
	 * ԭ�����ھ����߼��Ե������������Ҳ��������ԡ�
	 * 
	 * ��������ӡ˳������ˣ�Ӧ��ÿ��1��100�ټ�1��1��
	 * �����������������ΪaddAndGet()������ԭ�ӵģ�
	 * �������ͷ���֮��ĵ���ȴ����ԭ�ӵġ����������
	 * �������Ҫ��ͬ����
	 * @param args
	 * @throws InterruptedException
	 * 2017��8��20�� ����3:39:47
	 */
	public static void main6(String[] args) throws InterruptedException {
		AtomicIntergerNoSafe ai = new AtomicIntergerNoSafe();
		AtomicThread[] array = new AtomicThread[5];
		for (int i = 0; i < array.length; i++) {
			array[i] = new AtomicThread(ai);
		}
		for (int i = 0; i < array.length; i++) {
			array[i].start();
		}
		
		Thread.sleep(1000);
		System.out.println(ai.aiRef.get());
	}
	/**
	 * synchronized�������volatileͬ���Ĺ���
	 * 
	 * synchronized ����ʹ����̷߳���ͬһ����Դ����ͬ���ԣ������������н��̹߳����ڴ��е�
	 * ˽�б����빫���ڴ��еı���ͬ���Ĺ��ܡ�
	 * 
	 * ��-server������ģʽ���У�������ѭ�����õ�������
	 * �Ǹ��̼߳������ֵû�п�������ɵģ���synchronized���Ծ��п����ԡ�
	 * 
	 * @param args
	 * @throws InterruptedException
	 * 2017��8��20�� ����3:57:03
	 */
	public static void main(String[] args) throws InterruptedException {
		SynchronizedUpdateNewValue service = new SynchronizedUpdateNewValue();
		SynchronizedUpdateNewValueThreadA a = new SynchronizedUpdateNewValueThreadA(service);
		a.start();
		Thread.sleep(1000);
		SynchronizedUpdateNewValueThreadB b = new SynchronizedUpdateNewValueThreadB(service);
		b.start();
		System.out.println("�Ѿ�����ֹͣ�����");
	}

}
class VtdPrintString{
	private boolean isContinuePrint =  true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	public void printStringMethod(){
		try {
			while (isContinuePrint == true) {
				System.out.println("run printStringMethod threadName="
						+ Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class VtdPrintString1 implements Runnable{
	private boolean isContinuePrint =  true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	public void printStringMethod(){
		try {
			while (isContinuePrint == true) {
				System.out.println("run printStringMethod threadName="
						+ Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		printStringMethod();
	}
}

class VtdRunThread extends Thread{
//	private boolean isRunning = true;//��JVM����Ϊ -serverʱ������ѭ��
	private volatile boolean isRunning = true;//��JVM����Ϊ -serverʱ���������ѭ����ǿ�ƵĴӹ����ڴ��ж�ȡ������ֵ
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		System.out.println("���� run ��");
		while(isRunning == true){
			
		}
		System.out.println("�̱߳�ֹͣ�ˣ�");
	}
	
}

class VolatileTestThread extends Thread{
	public volatile static int count;
	
//	private static void addCount(){//��ͬ��
	//����ʹ��synchronized��Ҳ��û�б�Ҫʹ��volatile������count������
	private synchronized static void addCount(){//һ��Ҫ���static������synchronized��static�������ݾ�������ˣ�Ҳ�ʹﵽͬ����Ч����
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("threadName=" + Thread.currentThread().getName() + "       count=" + count);
	}
	@Override
	public void run() {
		addCount();
	}
}

class AtomicIntegerTest extends Thread{
	private AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("threadName=" + Thread.currentThread().getName() + "-------------->count=" + count.incrementAndGet());
		}
	}
}
class AtomicIntergerNoSafe{
	public static AtomicLong aiRef = new AtomicLong();
//	public void addNum(){//��ͬ��
	public synchronized void addNum(){//ͬ��
		System.out.println(Thread.currentThread().getName() + "����100֮���ֵ�ǣ�" + aiRef.addAndGet(100));
		aiRef.addAndGet(1);
	}
}
class AtomicThread extends Thread{
	private AtomicIntergerNoSafe ai;

	public AtomicThread(AtomicIntergerNoSafe ai) {
		super();
		this.ai = ai;
	}

	@Override
	public void run() {
		ai.addNum();
	}
	
}

class SynchronizedUpdateNewValue{
	private boolean isContinueRun = true;
	//û�п�����
	/*public void runMethod(){
		while(isContinueRun == true){
			
		}
		System.out.println("ͣ�����ˣ�");
	}*/
	//�޸ĺ��п�����
	public void runMethod(){
		String anyString = new String();
		while(isContinueRun == true){
			synchronized (anyString) {
				
			}
		}
		System.out.println("ͣ�����ˣ�");
	}
	public void stopMethod(){
		isContinueRun = false;
	}
}
class SynchronizedUpdateNewValueThreadA extends Thread{
	private SynchronizedUpdateNewValue service;

	public SynchronizedUpdateNewValueThreadA(SynchronizedUpdateNewValue service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.runMethod();
	}
	
}

class SynchronizedUpdateNewValueThreadB extends Thread{
	private SynchronizedUpdateNewValue service;

	public SynchronizedUpdateNewValueThreadB(SynchronizedUpdateNewValue service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.stopMethod();
	}
	
}