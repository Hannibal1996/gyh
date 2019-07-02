package com.itheima.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_03 {
	
	@Test
	public void demo01() throws Exception{
		//ģ������
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setId("id001");
		beanConfig.setClassName("com.itheima.domain.User");
		
		beanConfig.getProps().setProperty("uid", "u001");
		beanConfig.getProps().setProperty("userName", "jack");
		beanConfig.getProps().setProperty("password", "1234");
		
		//ʹ�����ݴ���JavaBeanʵ������ΪJavaBean��װ��������
		//1 ����ʵ����ʹ�÷���
		Class clazz = Class.forName(beanConfig.getClassName());
		Object obj = clazz.newInstance();
		
		//2 ����JavaBean��setter�����������ݷ�װ
		for(String name : beanConfig.getProps().stringPropertyNames()){
			String value = beanConfig.getProps().getProperty(name);
			// ��÷������� : setǰ׺ + ��д����ĸ + ��������
			String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
			// ����setter����
			Method method = clazz.getMethod(methodName, String.class);
			method.invoke(obj, value);
			
		}
		
		
		System.out.println(obj);
		
	}

}
