package com.lzg.booktest.t1t2;

import com.lzg.booktest.t1t2.OutClass1.Inner1;
import com.lzg.booktest.t1t2.OutClass2.Inner2;
import com.lzg.booktest.t1t2.OutClass2.InnerClass1;
import com.lzg.booktest.t1t2.PublicClass.PrivateClass;
import com.lzg.booktest.t1t2.PublicStaticClass.PrivateStaticClass;

/**
 * synchronized�ؼ������������ʹ�� 
 * 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��17�� ����10:37:16 
 */
public class InnerClass {

	public static void main1(String[] args) {
		PublicClass publicClass = new PublicClass();
		publicClass.setUsername("u");
		publicClass.setPassword("p");
		System.out.println(publicClass.getUsername() + " " + publicClass.getPassword());
		PrivateClass privateClass = publicClass.new PrivateClass();//ʵ����������
		privateClass.setAddress("a");
		privateClass.setAge("age");
		System.out.println(privateClass.getAge() + " " + privateClass.getAddress());
	}
	
	public static void main2(String[] args) {
		PublicStaticClass psc = new PublicStaticClass();
		psc.setUsername("username");
		psc.setPassword("password");
		System.out.println(psc.getUsername() + " " +psc.getPassword());
		
		PrivateStaticClass privateStaticClass = new PrivateStaticClass();
		privateStaticClass.setAge("20");
		privateStaticClass.setAddress("add");
		System.out.println(privateStaticClass.getAge() + " " + privateStaticClass.getAddress());
	}
	/**
	 * ����������������ͬ����������ʹ�õ�
	 * ȴ�ǲ�ͬ��������ӡ�Ľ��Ҳ���첽�ġ�����ġ�
	 * @param args
	 * 2017��8��17�� ����11:03:39
	 */
	public static void main3(String[] args) {
		final Inner1 inner = new Inner1();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				inner.method1();
			}
		},"A");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				inner.method2();
			}
		},"B");
		
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args) {
		final Inner2 in1 = new Inner2();
		final InnerClass1 in2 = new InnerClass1();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in1.method1(in2);
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in1.method2();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in2.method1();
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}

}
class PublicClass{
	private String username;
	private String password;
	class PrivateClass{
		private String age;
		private String address;
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void printPublicProperty(){
			System.out.println(username + " " + password);
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
//��̬������
class PublicStaticClass{
	private static String username;
	private static String password;
	static class PrivateStaticClass{
		private String age;
		private String address;
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void printPublicProperty(){
			System.out.println(username + " " + password);
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
class OutClass1 {
	static class Inner1{
		public void method1(){
			synchronized("��������"){
				for (int i = 0; i <=10; i++) {
					System.out.println(Thread.currentThread().getName() + "i=" + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		public synchronized void method2(){
			for (int i = 11; i <=20; i++) {
				System.out.println(Thread.currentThread().getName() + "i=" +i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class OutClass2 {
	static class Inner2{
		public void method1(InnerClass1 ic){
			String threadName = Thread.currentThread().getName();
			synchronized(ic){//�� ic�����������߳�ֻ����ͬ���ķ�ʽ����ic�еľ�̬ͬ��������
				System.out.println(threadName + " ����Inner2���е�method1����");
				for (int i = 0; i < 10; i++) {
					System.out.println("i=" + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(threadName + "�뿪Inner2���е�method1����");
			}
		}
		
		public synchronized void method2(){

			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " ����Inner2���е�method2����");
			for (int j= 0; j < 10; j++) {
				System.out.println("j=" + j);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(threadName + " �뿪Inner2���е�method2����");
		}
	}

	static class InnerClass1{
		public synchronized void method1(){
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " ����InnerClass1���е�method1����");
			for (int k = 0; k < 10; k++) {
				System.out.println("k=" + k);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(threadName + " �뿪InnerClass1���е�method1����");
		}
	}
}