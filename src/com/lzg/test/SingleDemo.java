package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月5日 下午10:47:04 
 * 
 * 多线程下的单例
 * 
 */
public class SingleDemo {

}

/**
 * 饿汉式
 * 多线程下安全
 *
 */
class EagerSingleton {
	private static final EagerSingleton  s=new EagerSingleton ();
	private EagerSingleton (){

	}
	public static EagerSingleton  getInstance(){
		return s;
	}
}

/**
 * 懒汉式
 * 
 *
 */
class LazySingleton{
	private static LazySingleton s=null;
	private LazySingleton(){
		
	}
//	public static  synchronized LazySingleton getInstance(){//同步函数可以解决多线程的安全问题，却降低了执行效率
	public static  LazySingleton getInstance(){
		if(s == null){//解决执行效率问题
			synchronized (LazySingleton.class) {//解决线程安全问题
				if(s == null){
					s = new LazySingleton();
				}
			}
		}
		
		return s;
	}
}
