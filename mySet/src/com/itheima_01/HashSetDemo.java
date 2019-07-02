package com.itheima_01;

import java.util.HashSet;

/*
 *	使用HashSet存储字符串并遍历 	
 *	
 *	Set的特点：
 *			 无序（存储和读取的顺序可能不一样）
 *			不允许重复
 *			没有整数索引

 */
public class HashSetDemo {
	public static void main(String[] args) {
		//创建集合对象
		//Set set = new HashSet();
		HashSet<String> set = new HashSet<String>();
		//添加元素
		set.add("hello");
		set.add("world");
		System.out.println(set.add("java"));
		System.out.println(set.add("java"));
		
		//遍历集合对象
		for(String s : set) {
			System.out.println(s);
		}
		
	}
}
