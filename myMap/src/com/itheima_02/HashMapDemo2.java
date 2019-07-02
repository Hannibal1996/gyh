package com.itheima_02;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 
 * ʹ��HashMap�洢���ݲ��������Զ��������Ϊkey��
 * 
 */
public class HashMapDemo2 {
	public static void main(String[] args) {
		//����Map����
		HashMap<Student,String> hm = new HashMap<Student,String>();
		//����key����
		Student s = new Student("zhangsan",18);
		Student s2 = new Student("lisi",20);
		Student s3 = new Student("lisi",20);
		
		//���ӳ���ϵ
		hm.put(s, "ITCAST001");
		hm.put(s2, "ITCAST002");
		hm.put(s3, "ITCAST002");
		
		//����Map����
		//��ʽ1�� ��ȡ���е�key��ͨ��key����ȡvalue
		Set<Student> keys = hm.keySet();
		for (Student key : keys) {
			String value = hm.get(key);
			System.out.println(key + "=" + value);
		}
		System.out.println("-----");
		
		//��ʽ2����ȡ���н��֤����ͨ�����֤�����ȡ�ɷ��ϱ��
		Set<Map.Entry<Student, String>> entrys = hm.entrySet();
		for (Entry<Student, String> entry : entrys) {
			Student key = entry.getKey();
			String value = entry.getValue();
			
			System.out.println(key + "=" + value);
		}
		
	}
}
