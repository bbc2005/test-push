package com.lzg.booktest.t4.s1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月28日 下午10:21:18 
 */
public class ReentrantLockTest {
	/**
	 * 使用ReentrantLock实现同步：测试1
	 * @param args
	 * 2017年8月28日 下午10:21:41
	 */
	public static void main1(String[] args) {
		RlService service = new RlService();
		RlThread t1 = new RlThread(service);
		RlThread t2 = new RlThread(service);
		RlThread t3 = new RlThread(service);
		RlThread t4 = new RlThread(service);
		RlThread t5 = new RlThread(service);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	/**
	 * 使用ReentrantLock实现同步：测试2
	 * 调用lock.lock()代码的线程就持有了“对象监视器”，其他线程
	 * 只有等待锁被释放时再次争抢。
	 * @param args
	 * 2017年8月28日 下午10:28:18
	 * @throws InterruptedException 
	 */
	public static void main2(String[] args) throws InterruptedException {
		ConditionService service = new ConditionService();
		ConditionThreadA a = new ConditionThreadA(service);
		a.setName("A");
		a.start();
		
		ConditionThreadAA aa = new ConditionThreadAA(service);
		aa.setName("AA");
		aa.start();
		
		Thread.sleep(100);
		
		ConditionThreadB b = new ConditionThreadB(service);
		b.setName("B");
		b.start();
		
		ConditionThreadBB bb = new ConditionThreadBB(service);
		bb.setName("BB");
		bb.start();
		
	}
	/**
	 * 使用Condition实现等待/通知：错误用法与解决
	 * 异常信息：监视器出错，解决办法：必须在condition.await()
	 * 方法调用之前调用lock.lock()代码获得同步监视器。
	 * @param args
	 * 2017年8月28日 下午10:43:39
	 */
	public static void main3(String[] args) {
		UseConditionWaitNotifyErrorService service = new UseConditionWaitNotifyErrorService();
		UcwnThreadA a = new UcwnThreadA(service);
		a.start();
	}
	
	public static void main4(String[] args) {
		UcwnService service = new UcwnService();
		UcThreadA a = new UcThreadA(service);
		a.start();
	}
	/**
	 * 正常使用Condition实现等待/通知
	 * 
	 * @param args
	 * 2017年8月29日 下午9:26:02
	 * @throws InterruptedException 
	 */
	public static void main5(String[] args) throws InterruptedException {
		UseConditionWaitNotifyOKService service = new UseConditionWaitNotifyOKService();
		UcoThreadA a = new UcoThreadA(service);
		a.start();
		Thread.sleep(3000);
		service.signal();
	}
	/**
	 * 使用多个Condition实现通知部分线程：错误用法
	 * @param args
	 * 2017年8月29日 下午9:37:10
	 */
	public static void main6(String[] args) {
		try {
			MustUseMoreConditionErrorService service = new MustUseMoreConditionErrorService();
			MumcThreadA a = new MumcThreadA(service);
			a.setName("A");
			a.start();
			MumcThreadB b = new MumcThreadB(service);
			b.setName("B");
			b.start();
			Thread.sleep(3000);
			service.SignalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 使用多个Condition实现通知部分线程：正确用法
	 * 使用ReentrantLock对象可以唤醒指定种类的线程
	 * @param args
	 * 2017年8月29日 下午10:01:58
	 * @throws InterruptedException 
	 */
	public static void main7(String[] args) throws InterruptedException {
		MustUserMoreConditionOKService service = new MustUserMoreConditionOKService();
		MuoThreadA a = new MuoThreadA(service);
		a.setName("A");
		a.start();
		
		MuoThreadB b = new MuoThreadB(service);
		b.setName("B");
		b.start();
		Thread.sleep(3000);
		service.signalAll_A();
	}
	/**
	 * 实现生产者/消费者模式：一对一交替打印
	 * @param args
	 * 2017年8月29日 下午10:28:03
	 */
	public static void main8(String[] args) {
		ConditionTestService service = new ConditionTestService();
		ConditionTestThreadA a = new ConditionTestThreadA(service);
		a.start();
		
		ConditionTestThreadB b = new ConditionTestThreadB(service);
		b.start();
	}
	/**
	 * 实现生产者/消费者模式：多对多交替打印
	 * @param args
	 * 2017年8月30日 下午10:07:19
	 */
	public static void main(String[] args) {
		ConditionTestManyToManyService service = new ConditionTestManyToManyService();
		ConditionTestManyToManyThreadA[] threadA = new ConditionTestManyToManyThreadA[10];
		ConditionTestManyToManyThreadB[] threadB = new ConditionTestManyToManyThreadB[10];
		for (int i = 0; i < 10; i++) {
			threadA[i] = new ConditionTestManyToManyThreadA(service);
			threadB[i] = new ConditionTestManyToManyThreadB(service);
			threadA[i].start();
			threadB[i].start();
		}
	}
}
class RlService{
	private Lock lock = new ReentrantLock();
	public void test(){
		lock.lock();
		for (int i = 0; i < 5; i++) {
			System.out.println("threadName=" + Thread.currentThread().getName() + "  " + (i + 1));
		}
		lock.unlock();
	}
}
class RlThread extends Thread{
	private RlService service;

	public RlThread(RlService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.test();
	}
	
}
class ConditionService{
	private Lock lock = new ReentrantLock();
	public void methodA(){
		try {
			lock.lock();
			System.out.println("methodA begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodA   end threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void methodB(){
		try {
			lock.lock();
			System.out.println("methodB begin threadName"
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodB   end threadName"
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
}

class ConditionThreadA extends Thread{
	private ConditionService service;

	public ConditionThreadA(ConditionService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodA();
	}
	
}
class ConditionThreadAA extends Thread{
	private ConditionService service;

	public ConditionThreadAA(ConditionService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodA();
	}
	
}
class ConditionThreadB extends Thread{
	private ConditionService service;

	public ConditionThreadB(ConditionService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodB();
	}
	
}
class ConditionThreadBB extends Thread{
	private ConditionService service;

	public ConditionThreadBB(ConditionService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodB();
	}
	
}

class UseConditionWaitNotifyErrorService{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void await(){
		try {
//			lock.lock();//加上正常，去掉异常
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class UcwnThreadA extends Thread{
	private UseConditionWaitNotifyErrorService service;

	public UcwnThreadA(UseConditionWaitNotifyErrorService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.await();
	}
	
}
class UcwnService{
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void waitMethod(){
		try {
			lock.lock();
			System.out.println("a");
			condition.await();
			System.out.println("b");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
			System.out.println("锁释放了！");
		}
	}
}
class UcThreadA extends Thread{
	private UcwnService service;

	public UcThreadA(UcwnService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.waitMethod();
	}
	
}
class UseConditionWaitNotifyOKService{
	private Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	public void await(){
		try {
			lock.lock();
			System.out.println(" await时间为" + System.currentTimeMillis());
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void signal(){
		try {
			lock.lock();
			System.out.println("signal时间为" + System.currentTimeMillis());
			condition.signal();
		} finally{
			lock.unlock();
		}
	}
}

class UcoThreadA extends Thread{
	private UseConditionWaitNotifyOKService service;

	public UcoThreadA(UseConditionWaitNotifyOKService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.await();
	}
	
}
class MustUseMoreConditionErrorService{
	private Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();
	public void awaitA(){
		try {
			lock.lock();
			System.out.println("begin awaitA时间为" + System.currentTimeMillis()
					+ " threadName=" + Thread.currentThread().getName());
			condition.await();
			System.out.println("  end awaitA时间为" + System.currentTimeMillis()
					+ " threadName=" + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void awaitB(){
		try {
			lock.lock();
			System.out.println("begin awaitB时间为" + System.currentTimeMillis()
					+ " threadName=" + Thread.currentThread().getName());
			condition.await();
			System.out.println("  end awaitB时间为" + System.currentTimeMillis()
					+ " threadName=" + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void SignalAll(){
		try {
			lock.lock();
			System.out.println(" signalAll时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
			condition.signalAll();
		} finally{
			lock.unlock();
		}
	}
}
class MumcThreadA extends Thread{
	private MustUseMoreConditionErrorService service;

	public MumcThreadA(MustUseMoreConditionErrorService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitA();
	}
	
}

class MumcThreadB extends Thread{
	private MustUseMoreConditionErrorService service;

	public MumcThreadB(MustUseMoreConditionErrorService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitB();
	}
	
}
class MustUserMoreConditionOKService{
	private Lock lock = new ReentrantLock();
	public Condition conditionA = lock.newCondition();
	public Condition conditionB = lock.newCondition();
	public void awaitA(){
		try {
			lock.lock();
			System.out.println("begin awaitA 时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
			conditionA.await();
			System.out.println("  end awaitA 时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void awaitB(){
		try {
			lock.lock();
			System.out.println("begin awaitB 时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
			conditionB.await();
			System.out.println("  end awaitB 时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void signalAll_A(){
		try {
			lock.lock();
			System.out.println("  signalAll_A时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
			conditionA.signalAll();
		} finally{
			lock.unlock();
		}
	}
	
	public void signalAll_B(){
		try {
			lock.lock();
			System.out.println("  signalAll_B时间为" + System.currentTimeMillis()
					+ "threadName=" + Thread.currentThread().getName());
			conditionB.signalAll();
		} finally{
			lock.unlock();
		}
	}
}
class MuoThreadA extends Thread{
	private MustUserMoreConditionOKService service;

	public MuoThreadA(MustUserMoreConditionOKService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitA();
	}
	
}

class MuoThreadB extends Thread{
	private MustUserMoreConditionOKService service;

	public MuoThreadB(MustUserMoreConditionOKService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitB();
	}
	
}
class ConditionTestService{
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;
	public void set(){
		try {
			lock.lock();
			while (hasValue == true) {
				condition.await();
			}
			System.out.println("打印★");
			hasValue = true;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void get(){
		try {
			lock.lock();
			while (hasValue == false) {
				condition.await();
			}
			System.out.println("打印☆");
			hasValue = false;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class ConditionTestThreadA extends Thread{
	private ConditionTestService service;

	public ConditionTestThreadA(ConditionTestService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.set();
		}
	}
	
}
class ConditionTestThreadB extends Thread{
	private ConditionTestService service;

	public ConditionTestThreadB(ConditionTestService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.get();
		}
	}
	
}

class ConditionTestManyToManyService{
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;
	public void set(){
		try {
			lock.lock();
			while (hasValue == true) {
				System.out.println("有可能★★连续");
				condition.await();
			}
			System.out.println("打印★");
			hasValue = true;
//			condition.signal();//会出现假死
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void get(){
		try {
			lock.lock();
			while (hasValue == false) {
				System.out.println("有可能☆☆连续");
				condition.await();
			}
			System.out.println("打印☆");
			hasValue = false;
//			condition.signal();//会出现假死
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

class ConditionTestManyToManyThreadA extends Thread{
	private ConditionTestManyToManyService service;

	public ConditionTestManyToManyThreadA(ConditionTestManyToManyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.set();
		}
	}
	
}
class ConditionTestManyToManyThreadB extends Thread{
	private ConditionTestManyToManyService service;

	public ConditionTestManyToManyThreadB(ConditionTestManyToManyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			service.get();
		}
	}
	
}