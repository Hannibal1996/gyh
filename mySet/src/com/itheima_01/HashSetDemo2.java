package com.itheima_01;

import java.util.HashSet;

/*
 *	ʹ��HashSet�洢�Զ�����󲢱��� 	
 *	ͨ���鿴Դ�뷢�֣�
 *				HashSet��add()���������Ȼ�ʹ�õ�ǰ�����е�ÿһ��Ԫ�غ�����ӵ�Ԫ�ؽ���hashֵ�Ƚϣ�
 *				���hashֵ��һ������ֱ������µ�Ԫ��
 *				���hashֵһ�����Ƚϵ�ֵַ����ʹ��equals�������бȽ�
 *				�ȽϽ��һ��������Ϊ���ظ������
 *				���еıȽϽ������һ�������

 */
public class HashSetDemo2 {
	public static void main(String[] args) {
		//�������϶���
		HashSet<Student> hs = new HashSet<Student>();
		//����Ԫ�ض���
		Student s = new Student("zhangsan",18);
		Student s2 = new Student("lisi",19);
		Student s3 = new Student("lisi",19);
		//���Ԫ�ض���
		hs.add(s);
		hs.add(s2);
		hs.add(s3);
		//�������϶���
		for (Student student : hs) {
			System.out.println(student);
		}
		 
	}
		 
}

class Student {
	String name;
	int age;
	
	public Student(String name,int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		//System.out.println("-------------------");
		Student s = (Student)obj;//����ת�ͣ����Ի�ȡ�������г�Ա
		
		//�Ƚ������Ƿ���ȣ���������򷵻�false
		if(this.age != s.age) {
			return false;
		}
		
		//�Ƚ������Ƿ���ȣ���������򷵻�false
		if(!this.name.equals(s.name)) {
			return false;
		}
		
		//Ĭ�Ϸ���true��˵������ѧ������ȵ�
		return true;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
}
