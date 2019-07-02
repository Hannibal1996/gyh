package com.itheima_01;

import java.util.ArrayList;

/*
 * ArrayList集合的遍历
 * 		通过size()和get()配合实现的
 */
public class ArrayListDemo3 {
	public static void main(String[] args) {
		//创建集合对象
		ArrayList<String> array = new ArrayList<String>();
		
		//添加元素
		array.add("hello");
		array.add("world");
		array.add("java");
		
		//获取元素
		//原始做法
		System.out.println(array.get(0));
		System.out.println(array.get(1));
		System.out.println(array.get(2));
		System.out.println("----------");
		
		for(int x=0; x<3; x++) {
			System.out.println(array.get(x));
		}
		System.out.println("----------");
		
		//如何知道集合中元素的个数呢?size()
		for(int x=0; x<array.size(); x++) {
			System.out.println(array.get(x));
		}
		System.out.println("----------");
		
		//最标准的用法
		for(int x=0; x<array.size(); x++) {
			String s = array.get(x);
			System.out.println(s);
		}
	}
}
