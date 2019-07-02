package com.itheima_02;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * 
 * ʹ��HashMap�洢���ݲ��������ַ�����Ϊkey��
 * 
 */
public class HashMapDemo {
	public static void main(String[] args) {
		//����Map����
		HashMap<String,String> hm = new HashMap<String,String>();
		//����ӳ���ϵ
		hm.put("ITCAST001", "����");
		hm.put("ITCAST002", "����");
		hm.put("ITCAST003", "����");
		hm.put("ITCAST003", "����");
		//����Map����
		
		//��ʽ1 ��ȡ���е�key��ͨ��key����ȡvalue
		Set<String> keys = hm.keySet();
		for (String key : keys) {
			String value = hm.get(key);
			System.out.println(key + "=" + value);
		}
		
		System.out.println("------------------");
		
		//��ʽ2����ȡ���еĽ��֤����Ȼ��ͨ�����֤�����ȡ�ɷ��ϱ��
		Set<Map.Entry<String, String>> entrys = hm.entrySet();
		for (Map.Entry<String, String> entry : entrys) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "=" + value);
		}
	}
}