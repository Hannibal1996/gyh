package com.itheima.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_02 {
	
	@Test
	public void demo01() throws Exception{
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
		
		
		System.out.println(beanConfig);
	}

}
