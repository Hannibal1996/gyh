package test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.User;

public class SpringTest {
	@Test
	public void a(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		User u = (User) ac.getBean("user");
		System.out.println(u);
	}

}
