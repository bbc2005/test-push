package com.lzg.booktest.t1t2;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月15日 下午9:50:07 
 */
public class ThreadDemo15 {

	public static void main(String[] args) throws InterruptedException {
		MyOneList list = new MyOneList();
		ThreadTest15A a =new ThreadTest15A(list);
		a.setName("A");
		a.start();
		
		ThreadTest15B b = new ThreadTest15B(list);
		b.setName("B");
		b.start();
		
		Thread.sleep(6000);
		System.out.println("list size:" + list.getSize());
	}

}
class MyOneList{
	private List list = new ArrayList();
	public synchronized void add(String data){
		list.add(data);
	}
	public int getSize(){
		return list.size();
	}
}
class ThreadDemo15Service{
	public MyOneList addService(MyOneList list,String data){
		try {
			synchronized(list){//解决办法：“同步化”
				if (list.getSize() < 1) {//“脏读”在这里出现，两个线程以异步的方式返回list参数的size()大小
					Thread.sleep(2000);//模拟从远程花费2秒取回数据
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
class ThreadTest15A extends Thread{
	private MyOneList list;
	public ThreadTest15A(MyOneList list){
		this.list = list;
	}
	@Override
	public void run() {
		ThreadDemo15Service service = new ThreadDemo15Service();
		service.addService(list, "A");
	}
}
class ThreadTest15B extends Thread{
	private MyOneList list;
	public ThreadTest15B(MyOneList list){
		this.list = list;
	}
	@Override
	public void run() {
		ThreadDemo15Service service = new ThreadDemo15Service();
		service.addService(list, "B");
	}
}