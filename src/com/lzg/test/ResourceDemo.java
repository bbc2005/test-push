package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��6�� ����11:24:07 
 * 
 * �̼߳�ͨѶ��
 * ����߳��ڴ���ͬһ��Դ����������ȴ��ͬ��
 */
public class ResourceDemo {
	public static void main(String[] args) {
		Resource r = new Resource();
		Input in = new Input(r);
		Output out = new Output(r);
		
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		
		t1.start();
		t2.start();
	}
}
//��Դ
class Resource{
	String name;
	String sex;
}
//����
class Input implements Runnable{
	Resource r;
	Input(Resource r){
		this.r=r;
	}
	@Override
	public void run() {
		int x = 0;
		while(true){
			synchronized (r) {
				if(x == 0){
					r.name="mike";
					r.sex="man";
				}else{
					r.name="����";
					r.sex="ŮŮŮ";
				}
			}
			
			x=(x+1)%2;
		}
	}
	
}

//���
class Output implements Runnable{
	
	Resource r ;
	public Output(Resource r) {
		this.r=r;
	}

	@Override
	public void run() {
		while(true){
			synchronized (r) {
				System.out.println(r.name + "......" + r.sex);
			}
		}
//		synchronized (r) {//����Ϊʲôÿ�ζ���ͬһ������������֮��������ѭ������һֱδ�ͷţ����������޷����¸�ֵ����һֱ��ʾһ�������������
//			int x=0;
//			while(true){
//				System.out.println(x + "......" + r.name + "......" + r.sex);
//				x++;
//			}
//		}
	}
	
}