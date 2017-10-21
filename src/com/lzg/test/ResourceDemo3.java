package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月6日 下午3:39:53 
 * 
 * 线程间通信  等待唤醒机制 代码优化
 */
public class ResourceDemo3 {
	public static void main(String[] args) {
		Resource2 r = new Resource2();
		Input2 in = new Input2(r);
		Output2 out = new Output2(r);
		
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		
		t1.start();
		t2.start();
	}
}
//资源
class Resource2{
	private String name;
	private String sex;
	private boolean flag = false;
	
	public synchronized void set(String name, String sex) {
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.name = name;
		this.sex = sex;
		flag=true;
		this.notify();
	}
	public synchronized void out(){
		if(! flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "......" + sex);
		flag=false;
		this.notify();
	}
	
	
}
//输入
class Input2 implements Runnable{
	Resource2 r;
	Input2(Resource2 r){
		this.r=r;
	}
	@Override
	public void run() {
//		System.out.println(r.flag + "-------------------------------------------");
		int x = 0;
		while(true){
			synchronized (r) {
				if(x == 0){
					r.set("mike", "man");
				}else{
					r.set("美丽", "女女女");
				}
			}
			
			x=(x+1)%2;
		}
	}
	
}

//输出
class Output2 implements Runnable{
	
	Resource2 r ;
	public Output2(Resource2 r) {
		this.r=r;
	}

	@Override
	public void run() {
		while(true){
			synchronized (r) {
				r.out();
			}
		}
	}
}