package com.itheima_01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 	Set<K> keySet()  
 * 	Collection<V> values() 
 */
public class MapDemo3 {
	public static void main(String[] args) {
		//����Map����
		Map<String,String> map = new HashMap<String,String>();
		//���ӳ���ϵ
		map.put("ITCAST001", "����");
		map.put("ITCAST002", "����");
		map.put("ITCAST005", "����");
		
		//Set<K> keySet() : ��Set����ʽ�񷵻����е�key
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println("-----------");
		
		//Collection<V> values() :
		Collection<String> values = map.values();
		for (String value : values) {
			System.out.println(value);
		}
		
		
	}
}
