package com.itheima.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_03 {
	
	@Test
	public void demo01() throws Exception{
		//模拟数据
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setId("id001");
		beanConfig.setClassName("com.itheima.domain.User");
		
		beanConfig.getProps().setProperty("uid", "u001");
		beanConfig.getProps().setProperty("userName", "jack");
		beanConfig.getProps().setProperty("password", "1234");
		
		//使用数据创建JavaBean实例，并为JavaBean封装具体数据
		//1 创建实例，使用反射
		Class clazz = Class.forName(beanConfig.getClassName());
		Object obj = clazz.newInstance();
		
		//2 调用JavaBean的setter方法进行数据封装
		for(String name : beanConfig.getProps().stringPropertyNames()){
			String value = beanConfig.getProps().getProperty(name);
			// 获得方法名称 : set前缀 + 大写首字母 + 其他内容
			String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
			// 调用setter方法
			Method method = clazz.getMethod(methodName, String.class);
			method.invoke(obj, value);
			
		}
		
		
		System.out.println(obj);
		
	}

}
