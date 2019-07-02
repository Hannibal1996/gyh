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
		
		 * 我们发现当hashCode方法永远返回整数1时，所有对象的hash值都是一样的，
		 * 有一些对象他的成员变量完全不同，但是他们还需要进行hash和equals方法的比较，
		 * 如果我们可以让成员变量不同的对象，他们的hash值也不同，这就可以减少一部分equals方法的比较
		 * 从而可以提高我们程序的效率
		 * 
		 * 可以尝试着让hashCode方法的返回值和对象的成员变量有关
		 * 可以让hashCode方法返回所有成员变量之和，
		 * 让基本数据类型直接想加，然后引用数据类型获取hashCode方法返回值后再相加（boolean不可以参与运算）
		 * 
		 
		//return age;
		return age + name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("-------------");
		
		//提高效率
		if(this == obj) {
			return true;
		}
		
		//提高健壮性
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		
		
		//向下转型
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
