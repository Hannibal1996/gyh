package com.itheima_03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Collections��
 * �����⣺Collection��Collections��ʲô����
 * 		Collection�Ǽ�����ϵ����㣬�����˼�����ϵ�Ĺ���
 * 		Collections��һ�������࣬�����������ڲ���Collection
 * 
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		//static void swap(List list, int i, int j) :��ָ���б��е�������������λ�û���
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(4);
		Collections.swap(list, 0, 1);
		
		System.out.println(list);
	
	}

	private static void method6() {
		//static void  sort(List<T> list) :�����б���Ԫ�ص���Ȼ˳���������
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(4);
		list.add(3);
		list.add(2);
		
		Collections.sort(list);
		System.out.println(list);
	}

	private static void method5() {
		//static void shuffle(List list):ɵ������û�  
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Collections.shuffle(list);
		System.out.println(list);
	}

	private static void method4() {
		//static void reverse(List list)  :��ת
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Collections.reverse(list);
		System.out.println(list);
	}

	private static void method3() {
		//static void fill(List list, Object obj) :ʹ��ָ���Ķ������ָ���б������Ԫ��
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		list.add("java");
		System.out.println(list);
		
		Collections.fill(list, "android");
		
		System.out.println(list);
	}

	private static void method2() {
		//static void copy(List dest, List src) :�ǰ�Դ�б��е����ݸ��ǵ�Ŀ���б�
		//ע�⣺Ŀ���б�ĳ������ٵ���Դ�б�ĳ���
		//����Դ�б�
		List<String> src = new ArrayList<String>();
		src.add("hello");
		src.add("world");
		src.add("java");
		
		//����Ŀ���б�
		List<String> dest = new ArrayList<String>();
		dest.add("java");
		dest.add("java");
		dest.add("java");
		dest.add("java");
		Collections.copy(dest, src);
		System.out.println(dest);
	}

	private static void method() {
		//static int  binarySearch(List list, Object key) ʹ�ö��ֲ��ҷ�����ָ��Ԫ����ָ���б������λ�� 
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		int index = Collections.binarySearch(list, 4);
		System.out.println(index);
	}
}
