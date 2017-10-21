package com.lzg.test;

import java.util.ArrayList;
import java.util.List;



/**
 * Java堆内存溢出测试
 * @author DELL
 *
 */
public class HeapOOm {
	
	static class OOMObject{
		
	}
	public static void main(String[] args) {
		//创建集合对象
		List<OOMObject> list = new ArrayList<HeapOOm.OOMObject>();
		
		while (true) {
			list.add(new OOMObject());
		}
		
	}

}
