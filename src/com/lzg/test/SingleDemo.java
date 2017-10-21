package com.lzg.test;
/** 
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��5�� ����10:47:04 
 * 
 * ���߳��µĵ���
 * 
 */
public class SingleDemo {

}

/**
 * ����ʽ
 * ���߳��°�ȫ
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
 * ����ʽ
 * 
 *
 */
class LazySingleton{
	private static LazySingleton s=null;
	private LazySingleton(){
		
	}
//	public static  synchronized LazySingleton getInstance(){//ͬ���������Խ�����̵߳İ�ȫ���⣬ȴ������ִ��Ч��
	public static  LazySingleton getInstance(){
		if(s == null){//���ִ��Ч������
			synchronized (LazySingleton.class) {//����̰߳�ȫ����
				if(s == null){
					s = new LazySingleton();
				}
			}
		}
		
		return s;
	}
}
