package com.itheima_02;

import java.util.HashSet;

public class HashSetDemo3 {
	public static void main(String[] args) {
		//�������϶���
		HashSet<Person> hs = new HashSet<Person>();
		//����Ԫ�ض���
		Person p = new Person("zhangsan",20);
		Person p2 = new Person("lisi",20);
		Person p3 = new Person("lisi",20);
		//���Ԫ�ض���
		hs.add(p);
		hs.add(p2);
		hs.add(p3);
		//�������϶���
		for (Person person : hs) {
			System.out.println(person);
		}
		
	}
}
