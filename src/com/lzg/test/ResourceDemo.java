package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 上午11:24:07 
 * 
 * 线程间通讯：
 * 多个线程在处理同一资源，但是任务却不同。
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
//资源
class Resource{
	String name;
	String sex;
}
//输入
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
					r.name="美丽";
					r.sex="女女女";
				}
			}
			
			x=(x+1)%2;
		}
	}
	
}

//输出
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
//		synchronized (r) {//这样为什么每次都是同一个？这里获得锁之后陷入死循环，锁一直未释放，所以输入无法重新赋值，就一直显示一个对象的属性了
//			int x=0;
//			while(true){
//				System.out.println(x + "......" + r.name + "......" + r.sex);
//				x++;
//			}
//		}
	}
	
}