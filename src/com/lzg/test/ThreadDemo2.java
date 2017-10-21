package com.lzg.test;

public class ThreadDemo2 {
	public static void main(String[] args) {
		Demo1 d1=new Demo1("aaa");
		Demo1 d2=new Demo1("µÄË¢¿¨½É·Ñ¿¨");
		d1.show();
		d2.show();
		
		System.out.println("Hello World!");
	}
}

class Demo1{
	private String name;
	Demo1(String name){
		this.name=name;
	}
	
	public void show(){
		for (int i = 0; i < 20; i++) {
			System.out.println(name+"---->i="+i);
		}
	}
}
