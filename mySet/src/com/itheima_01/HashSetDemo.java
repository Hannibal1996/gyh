package com.itheima_01;

import java.util.HashSet;

/*
 *	ʹ��HashSet�洢�ַ��������� 	
 *	
 *	Set���ص㣺
 *			 ���򣨴洢�Ͷ�ȡ��˳����ܲ�һ����
 *			�������ظ�
 *			û����������

 */
public class HashSetDemo {
	public static void main(String[] args) {
		//�������϶���
		//Set set = new HashSet();
		HashSet<String> set = new HashSet<String>();
		//���Ԫ��
		set.add("hello");
		set.add("world");
		System.out.println(set.add("java"));
		System.out.println(set.add("java"));
		
		//�������϶���
		for(String s : set) {
			System.out.println(s);
		}
		
	}
}
