package com.lzg.booktest.t1t2;
/**
 * synchronized�ؼ�������static��̬�����Ǹ�Class��������
 * ��synchronized���η�static��̬�����Ǹ����������� 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��16�� ����9:13:21 
 */
public class ThreadDemo17 {
	/**
	 * synchronized����static��̬�����ϣ�
	 * �ǶԵ�ǰ��*.java�ļ���Ӧ��Class����г�����
	 * @param args
	 * 2017��8��16�� ����9:30:32
	 */
	public static void main1(String[] args) {
		Td17A a = new Td17A();
		a.setName("A");
		a.start();
		
		Td17B b = new Td17B();
		b.setName("B");
		b.start();
	}
	/**
	 * �첽ԭ�򣺳��в�ͬ������һ���Ƕ�������������һ����Class����
	 * @param args
	 * 2017��8��16�� ����9:46:36
	 */
	public static void main2(String[] args) {
		Td17Service1 service = new Td17Service1();
		Td17A1 a = new Td17A1(service);
		a.setName("A");
		a.start();
		
		Td17B1 b = new Td17B1(service);
		b.setName("B");
		b.start();
		
		Td17C1 c = new Td17C1(service);
		c.setName("C");
		c.start();
	}
	/**
	 * Class�����Զ�������ж���ʵ�������á�
	 * @param args
	 * 2017��8��16�� ����9:49:52
	 */
	public static void main3(String[] args) {
		Td17Service1 service1 = new Td17Service1();
		Td17Service1 service2 = new Td17Service1();
		Td17A1 a = new Td17A1(service1);
		a.setName("A");
		a.start();
		
		Td17B1 b = new Td17B1(service2);
		b.setName("B");
		b.start();
	}
	/**
	 * synchronized(class)���������ú�synchronized static����������һ����
	 * @param args
	 * 2017��8��16�� ����9:57:26
	 */
	public static void main(String[] args) {
		Td17Service2 service1 = new Td17Service2();
		Td17Service2 service2 = new Td17Service2();
		
		Td17A2 a = new Td17A2(service1);
		a.setName("A");
		a.start();
		
		Td17B2 b = new Td17B2(service2);
		b.setName("B");
		b.start();
		
	}

}
class Td17Service{
	public static synchronized void printA(){
		try {
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "����printA");
			Thread.sleep(3000);
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "�뿪printA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void printB(){
		try {
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "����printB");
			Thread.sleep(3000);
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "�뿪printB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Td17A extends Thread{

	@Override
	public void run() {
		Td17Service.printA();
	}
	
}
class Td17B extends Thread{

	@Override
	public void run() {
		Td17Service.printB();
	}
	
}

class Td17Service1{
	public static synchronized void printA(){//Class�������Զ�������ж���ʵ�������á�
		try {
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "����printA");
			Thread.sleep(5000);
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "�뿪printA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static synchronized void printB(){//Class��
		try {
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "����printB");
			Thread.sleep(5000);
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "�뿪printB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void printC(){//������
		try {
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "����printC");
			Thread.sleep(5000);
			System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
					+ System.currentTimeMillis() + "�뿪printC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Td17A1 extends Thread{
	private Td17Service1 service;
	public Td17A1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printA();
	}
	
}
class Td17B1 extends Thread{
	private Td17Service1 service;
	public Td17B1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printB();
	}
	
}
class Td17C1 extends Thread{
	private Td17Service1 service;
	public Td17C1(Td17Service1 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printC();
	}
}

class Td17Service2{
	public static void printA(){
		synchronized(Td17Service2.class){
			try {
				System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
						+ System.currentTimeMillis() + "����printA");
				Thread.sleep(5000);
				System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
						+ System.currentTimeMillis() + "�뿪printA");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void printB(){
		synchronized(Td17Service2.class){
			try {
				System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
						+ System.currentTimeMillis() + "����printB");
				Thread.sleep(5000);
				System.out.println("�߳�����=" + Thread.currentThread().getName() + "��"
						+ System.currentTimeMillis() + "�뿪printB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Td17A2 extends Thread{
	private Td17Service2 service;
	public Td17A2(Td17Service2 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printA();
	}
	
}
class Td17B2 extends Thread{
	private Td17Service2 service;
	public Td17B2(Td17Service2 service){
		this.service = service;
	}
	@Override
	public void run() {
		service.printB();
	}
	
}
