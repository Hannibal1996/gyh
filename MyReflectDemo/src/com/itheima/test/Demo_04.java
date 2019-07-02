package com.itheima.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_04 {
	
	//��������ļ���������
	public BeanConfig getBean() throws Exception{
		//��ȡ�����ļ�����þ�����Ϣ
		BeanConfig beanConfig = new BeanConfig();
		//1 ��ȡbean.properties�ļ�������id��className����
		Properties beanProps = new Properties();
		beanProps.load(new InputStreamReader(new FileInputStream("bean.properties"), "UTF-8"));
		beanConfig.setId(beanProps.getProperty("id"));
		beanConfig.setClassName(beanProps.getProperty("className"));
		
		
		//2��ȡdata.properties�ļ�������getProps().setProperty()
		Properties dataProps = new Properties();
		dataProps.load(new InputStreamReader(new FileInputStream("data.properties"), "UTF-8"));
		for(String name : dataProps.stringPropertyNames()){
			String value = dataProps.getProperty(name);
			beanConfig.getProps().setProperty(name, value);
		}
		
		return beanConfig;
	}
	
	@Test
	public void demo01() throws Exception{
		//��ʵ��������
		BeanConfig beanConfig = getBean();

		
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
