package com.itheima;
/*
 * 创建一个学生数组，存储三个学生对象并遍历
 * 
 * 分析：
 * 		A:定义学生类
 * 		B:创建学生数组
 * 		C:创建学生对象
 * 		D:把学生对象作为元素赋值给学生数组
 * 		E:遍历学生数组
 */
public class StudentDemo {
	public static void main(String[] args) {
		//创建学生数组
		Student[] students = new Student[3];
		
		//创建学生对象
		Student s1 = new Student("曹操",40);
		Student s2 = new Student("刘备",35);
		Student s3 = new Student("孙权",30);
		
		//把学生对象作为元素赋值给学生数组
		students[0] = s1;
		students[1] = s2;
		students[2] = s3;
		
		//遍历学生数组
		for(int x=0; x<students.length; x++) {
			Student s = students[x];
			//System.out.println(s);
			System.out.println(s.getName()+"---"+s.getAge());
		}
	}
}
