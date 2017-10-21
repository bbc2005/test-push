package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月8日 下午10:24:46 
 */
public class ThreadRunnableDemo {
	public static void main(String[] args) {
		
		new Thread(){
			@Override
			public void run() {
				for (int x = 0; x < 50; x++) {
					System.out.println(Thread.currentThread().getName() + "......x=" +x);
				}
			}
			
		}.start();
		
		for (int y = 0; y < 50; y++) {
			System.out.println(Thread.currentThread().getName() + "......y=" +y);
		}
		
		Runnable r = new Runnable() {
			public void run() {
				for (int z = 0; z < 50; z++) {
					System.out.println(Thread.currentThread().getName() + "......z=" +z);
				}
			}
		};
		new Thread(r).start();
	}

}
