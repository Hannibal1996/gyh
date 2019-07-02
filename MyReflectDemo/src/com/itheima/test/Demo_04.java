package com.itheima.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_04 {
	
	//获得配置文件具体数据
	public BeanConfig getBean() throws Exception{
		//读取配置文件，获得具体信息
		BeanConfig beanConfig = new BeanConfig();
		//1 读取bean.properties文件，设置id和className属性
		Properties beanProps = new Properties();
		beanProps.load(new InputStreamReader(new FileInputStream("bean.properties"), "UTF-8"));
		beanConfig.setId(beanProps.getProperty("id"));
		beanConfig.setClassName(beanProps.getProperty("className"));
		
		
		//2读取data.properties文件，设置getProps().setProperty()
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
		//真实具体数据
		BeanConfig beanConfig = getBean();

		
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
