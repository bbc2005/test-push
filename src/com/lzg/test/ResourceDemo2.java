package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��6�� ����2:52:08 
 * 
 * �ȴ�/���ѻ��ơ�
 * �漰�ķ�����
 * 1��wait()�����̴߳��ڶ���״̬����wait���̻߳ᱻ�洢���̳߳��С�
 * 2��notify()�������̳߳��е�һ���̣߳����⣩��
 * 3��notifyAll()�������̳߳��е������̡߳�
 * 
 * ��Щ���������붨����ͬ���С�
 * ��Ϊ��Щ���������ڲ����߳�״̬�ķ�����
 * ����Ҫ��ȷ���ײ��������ĸ����ϵ��̡߳�
 * 
 * Ϊʲô�����̵߳ķ��� wait notify notifyAll��������Object���У�
 * 
 * ��Ϊ��Щ�����Ǽ������ķ�������������ʵ��������
 * ������������Ķ�������Ķ�����õķ���һ��������Object���С�
 */
public class ResourceDemo2 {
	public static void main(String[] args) {
		Resource1 r = new Resource1();
		Input1 in = new Input1(r);
		Output1 out = new Output1(r);
		
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		
		t1.start();
		t2.start();
	}
}
//��Դ
class Resource1{
	String name;
	String sex;
	boolean flag = false;
}
//����
class Input1 implements Runnable{
	Resource1 r;
	Input1(Resource1 r){
		this.r=r;
	}
	@Override
	public void run() {
//		System.out.println(r.flag + "-------------------------------------------");
		int x = 0;
		while(true){
			synchronized (r) {
				if(r.flag){
					try {
						r.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(x == 0){
					r.name="mike";
					r.sex="man";
				}else{
					r.name="����";
					r.sex="ŮŮŮ";
				}
				r.flag = true;
				r.notify();
				
			}
			
			x=(x+1)%2;
		}
	}
	
}

//���
class Output1 implements Runnable{
	
	Resource1 r ;
	public Output1(Resource1 r) {
		this.r=r;
	}

	@Override
	public void run() {
		while(true){
			synchronized (r) {
				if(! r.flag){
					try {
						r.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println(r.name + "......" + r.sex);
				r.flag=false;
				r.notify();
				
			}
		}
	}
}