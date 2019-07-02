package com.itheima_02;

public class Person {
	String name;
	int age;
	
	public Person(String name,int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/*
	@Override
	public int hashCode() {
		
		 * ���Ƿ��ֵ�hashCode������Զ��������1ʱ�����ж����hashֵ����һ���ģ�
		 * ��һЩ�������ĳ�Ա������ȫ��ͬ���������ǻ���Ҫ����hash��equals�����ıȽϣ�
		 * ������ǿ����ó�Ա������ͬ�Ķ������ǵ�hashֵҲ��ͬ����Ϳ��Լ���һ����equals�����ıȽ�
		 * �Ӷ�����������ǳ����Ч��
		 * 
		 * ���Գ�������hashCode�����ķ���ֵ�Ͷ���ĳ�Ա�����й�
		 * ������hashCode�����������г�Ա����֮�ͣ�
		 * �û�����������ֱ����ӣ�Ȼ�������������ͻ�ȡhashCode��������ֵ������ӣ�boolean�����Բ������㣩
		 * 
		 
		//return age;
		return age + name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("-------------");
		
		//���Ч��
		if(this == obj) {
			return true;
		}
		
		//��߽�׳��
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		
		
		//����ת��
		Person p = (Person)obj;
		
		if(!this.name.equals(p.name)) {
			return false;
		}
		
		if(this.age != p.age) {
			return false;
		}
		
		return true;
		
	}*/
	
	
	
}
