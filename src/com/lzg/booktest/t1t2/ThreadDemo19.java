package com.lzg.booktest.t1t2;
/** 
 * ������ĸı�
 * �ٽ��κ�����������Ϊͬ����ʱ����Ҫע����ǣ��Ƿ��ж���߳�ͬʱ�������������
 * ͬʱ������ͬ������������Щ�߳�֮�����ͬ���ģ�����ֱ�����������Щ�߳�֮��
 * �����첽�ġ�
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��19�� ����4:21:09 
 */
public class ThreadDemo19 {

	public static void main1(String[] args) throws InterruptedException {
		Td19Service service = new Td19Service();
		Td19A a = new Td19A(service);
		a.setName("a");
		Td19B b = new Td19B(service);
		b.setName("b");
		a.start();
		Thread.sleep(50);//ע�͵���ͬ���ģ���ͬһ������123������ע�����첽�ģ�һ�����ǡ�123����һ���ǡ�456��
		b.start();
	}
	/**
	 * ֻҪ���󲻱䣬��ʹ��������Ա��ı䣬���еĽ������ͬ����
	 * @param args
	 * @throws InterruptedException
	 * 2017��8��19�� ����4:48:21
	 */
	public static void main(String[] args) throws InterruptedException {
		Td19Service1 service = new Td19Service1();
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("AAAA");
		userInfo.setPassword("BBBB");
		System.out.println("username:" + userInfo.getUsername() + "\t password:" + userInfo.getPassword());
		Td19A1 a = new Td19A1(service, userInfo);
		a.setName("a");
		a.start();
		Thread.sleep(50);
		Td19B1 b = new Td19B1(service, userInfo);
		b.setName("b");
		b.start();
		Thread.sleep(6000);
		System.out.println("username:" + userInfo.getUsername() + "\t password:" + userInfo.getPassword());
	}

}
class Td19Service{
	private String lock = "123";
	public void test(){
		try {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " begin "
						+ System.currentTimeMillis());
				lock = "456";
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + "  end "
						+ System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Td19A extends Thread {
	private Td19Service service;
	public Td19A (Td19Service service){
		this.service = service;
	}
	@Override
	public void run() {
		service.test();
	}
}
class Td19B extends Thread {
	private Td19Service service;

	public Td19B(Td19Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.test();
	}
}
class UserInfo{
	private String username;
	private String password;
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
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
class Td19Service1 {
	public void serviceMethodA(UserInfo userInfo){
		try {
			synchronized (userInfo) {
				System.out.println(Thread.currentThread().getName());
				userInfo.setUsername("abcabcabc");
				userInfo.setPassword("88888888");
				Thread.sleep(3000);
				System.out.println("end ! time=" + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Td19A1 extends Thread{
	private Td19Service1 service;
	private UserInfo userInfo;
	public Td19A1(Td19Service1 service, UserInfo userInfo) {
		super();
		this.service = service;
		this.userInfo = userInfo;
	}
	@Override
	public void run() {
		service.serviceMethodA(userInfo);
	}
}
class Td19B1 extends Thread{
	private Td19Service1 service;
	private UserInfo userInfo;
	public Td19B1(Td19Service1 service, UserInfo userInfo) {
		super();
		this.service = service;
		this.userInfo = userInfo;
	}
	@Override
	public void run() {
		service.serviceMethodA(userInfo);
	}
}