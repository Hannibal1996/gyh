package com.itheima.test;

import org.junit.Test;

import com.itheima.domain.BeanConfig;

public class Demo_01 {
	
	@Test
	public void demo01(){
		//Ä£ÄâÊý¾Ý
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setId("id001");
		beanConfig.setClassName("com.itheima.domain.User");
		
		beanConfig.getProps().setProperty("uid", "u001");
		beanConfig.getProps().setProperty("userName", "jack");
		beanConfig.getProps().setProperty("password", "1234");
	}

}
