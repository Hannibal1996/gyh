package com.itheima_01;

import java.util.HashMap;
import java.util.Map;

/*
 * 	Map�ĳ��ù��ܣ�
 * 		ӳ�书�ܣ�
 * 			 	V put(K key, V value) 
 * 		��ȡ���ܣ�
 * 				V get(Object key) 
 * 				int size() 
 * 		�жϹ��ܣ�
 * 				boolean containsKey(Object key) 
 				boolean containsValue(Object value) 
 				boolean isEmpty() 
 
 * 		ɾ�����ܣ�
 * 				void clear()  
 * 				V remove(Object key)  
 * 
 * 		�������ܣ�
 * 				Set<Map.Entry<K,V>> entrySet() 
 * 
 * 
 * 			Set<K> keySet()  
 * 			Collection<V> values()  
 
 */
public class MapDemo2 {
	public static void main(String[] args) {
		//����Map����
		Map<String,String> map = new HashMap<String,String>();
		
		//V put(K key, V value) �����ǽ�keyӳ�䵽value�����key���ڣ��򸲸�value������ԭ����value����
		System.out.println(map.put("ITCAST001", "����"));
		System.out.println(map.put("ITCAST002", "����"));
		System.out.println(map.put("ITCAST001", "����"));
		
		//void clear() : ������еĶ�Ӧ��ϵ  
		//map.clear();
		
		//V remove(Object key) :����ָ����keyɾ����Ӧ��ϵ��������key����Ӧ��ֵ�����û��ɾ���ɹ��򷵻�null
		//System.out.println(map.remove("ITCAST005"));
		
		//boolean containsKey(Object key) : �ж�ָ��key�Ƿ����
		//System.out.println(map.containsKey("ITCAST003"));
		
		//boolean containsValue(Object value)���ж�ָ����value�Ƿ����
		//System.out.println(map.containsValue("����"));
		
		//boolean isEmpty() : �ж��Ƿ��ж�Ӧ��ϵ
		//System.out.println(map.isEmpty());
		
		//int size() : ���ض�Ӧ��ϵ�ĸ���
		//System.out.println(map.size());
		
		//V get(Object key) : ����ָ����key���ض�Ӧ��value
		System.out.println(map.get("ITCAST002"));
		
		System.out.println(map);
	}
}
