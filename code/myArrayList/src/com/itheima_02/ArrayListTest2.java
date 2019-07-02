package com.itheima_02;

import java.util.ArrayList;

/*
 * 给定一个字符串数组：{“张三丰”,“宋远桥”,“张无忌”,“殷梨亭”,“张翠山”,“莫声谷”}，将数组中的元素添加到集合中，并把所有姓张的人员打印到控制台上。
 * 
 * 分析：
 * 		A:定义字符串数组
 * 		B:创建集合对象
 * 		C:遍历字符串数组，获取到每一个字符串元素
 * 		D:把获取到的字符串元素添加到集合
 * 		E:遍历集合
 * 			要判断每一个字符串元素是否以"张"开头，如果是，就输出在控制台
 */
public class ArrayListTest2 {
	public static void main(String[] args) {
		//定义字符串数组
		String[] strArray = {"张三丰","宋远桥","张无忌","殷梨亭","张翠山","莫声谷"};
		
		//创建集合对象
		ArrayList<String> array = new ArrayList<String>();
		
		//遍历字符串数组，获取到每一个字符串元素
		for(int x=0; x<strArray.length; x++) {
			//把获取到的字符串元素添加到集合
			array.add(strArray[x]);
		}
		
		//遍历集合
		for(int x=0; x<array.size(); x++) {
			String s = array.get(x);
			//要判断每一个字符串元素是否以"张"开头，如果是，就输出在控制台
			if(s.startsWith("张")) {
				System.out.println(s);
			}
		}
	}
}
