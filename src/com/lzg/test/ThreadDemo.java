package com.lzg.test;
/**
 * Ä£ÄâÀ¬»ø»ØÊÕ
 * @author DELL
 *
 */
class Demo extends Object{
	public void finalize(){
		System.out.println("demo ok");
	}
}
public class ThreadDemo{
	public static void main(String[] args) {
		new Demo();
		new Demo();
		
		new Demo();
		System.gc();
		System.out.println("Hello World!");
	}
	
}
