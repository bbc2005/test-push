package com.lzg.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzg
 * @desc
 * @version 0.0.1
 * @time 创建时间：2017年8月27日 上午10:51:46
 */
public class StringDemo {
	public static void main(String[] args) {
		String str = "Hello ";
		str += "world";
		Map map = new HashMap();
		map.put(null, null);
		map.put("a", null);
		
		String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME =
				"org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";

		String[] ss = new String[] { ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME };
		System.out.println(ss.length);	
		System.out.println(ss[0]);

	}
}
