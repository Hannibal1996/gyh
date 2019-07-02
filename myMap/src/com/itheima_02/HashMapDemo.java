package com.itheima_02;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 
 * 使用HashMap存储数据并遍历（字符串作为key）
 * 
 */
public class HashMapDemo {
	public static void main(String[] args) {
		//创建Map对象
		HashMap<String,String> hm = new HashMap<String,String>();
		//添加映射关系
		hm.put("ITCAST001", "张三");
		hm.put("ITCAST002", "李四");
		hm.put("ITCAST003", "王五");
		hm.put("ITCAST003", "赵六");
		//遍历Map对象
		
		//方式1 获取所有的key，通过key来获取value
		Set<String> keys = hm.keySet();
		for (String key : keys) {
			String value = hm.get(key);
			System.out.println(key + "=" + value);
		}
		
		System.out.println("------------------");
		
		//方式2：获取所有的结婚证对象，然后通过结婚证对象获取丈夫和媳妇
		Set<Map.Entry<String, String>> entrys = hm.entrySet();
		for (Map.Entry<String, String> entry : entrys) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "=" + value);
		}
	}
}
