package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import user.User;

public class Testa {
	@Test
	public void testAdd(){
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setUsername("高永涵");
		user.setPassword("12138");
		user.setAddress("武汉");
		session.save(user);
		tx.commit();
		session.close();
		sessionFactory.close();
	}

}
