package com.lzg.test;

import java.util.ArrayList;
import java.util.List;



/**
 * Java���ڴ��������
 * @author DELL
 *
 */
public class HeapOOm {
	
	static class OOMObject{
		
	}
	public static void main(String[] args) {
		//�������϶���
		List<OOMObject> list = new ArrayList<HeapOOm.OOMObject>();
		
		while (true) {
			list.add(new OOMObject());
		}
		
	}

}
