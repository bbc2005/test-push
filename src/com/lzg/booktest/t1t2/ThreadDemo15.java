package com.lzg.booktest.t1t2;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��15�� ����9:50:07 
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
			synchronized(list){//����취����ͬ������
				if (list.getSize() < 1) {//���������������֣������߳����첽�ķ�ʽ����list������size()��С
					Thread.sleep(2000);//ģ���Զ�̻���2��ȡ������
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