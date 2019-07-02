package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import entity.Classify;
import entity.Goods;
import utils.HibernateUtils;

public class HibernateOnetoMany {
	
	//演示一对多修改
	@Test
	public void testUpdate() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			//1 根据id查询商品，根据id查询商品的分类
			Classify cf = session.get(Classify.class, "1");
			Goods gs = session.get(Goods.class, "3");
			//2 设置持久态对象值
			//把商品放到分类里面
			cf.getSetGoods().add(gs);
			//把分类放到商品里面
			gs.setClassify(cf);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
	//演示一对多级联删除
	@Test
	public void testDeleteDemo() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			// 1 根据id查询分类对象
			Classify cy = session.get(Classify.class,"4028b8816b1ce40c016b1ce40e470000"); 
			//2 调用方法删除
			session.delete(cy);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}

	//演示一对多级联保存
	@Test
	public void testAddDemo2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			// 添加一个分类，为这个分类添加两个商品
			//1 创建分类和商品对象
			Classify cy = new Classify();
			cy.setCname("用品");
			cy.setCdesc("生活用品");
			
			Goods gs1 = new Goods();
			gs1.setGname("剃须刀");
			gs1.setPrice("200");
			gs1.setGdesc("早上要刮胡子");
			Goods gs2 = new Goods();
			gs2.setGname("纸巾");
			gs2.setPrice("3");
			gs2.setGdesc("生活必需品");
			
			//2 把商品放到分类里面
			cy.getSetGoods().add(gs1);
			cy.getSetGoods().add(gs2);
			
			//3 保存分类
			session.save(cy);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}

	//演示一对多级联保存
	@Test
	public void testAddDemo1() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//得到session
			session = sessionFactory.openSession();
			//开启事务
			tx = session.beginTransaction();
			
			// 添加一个分类，为这个分类添加一个商品
			//1 创建分类和商品对象
			Classify cy = new Classify();
			cy.setCname("食品");
			cy.setCdesc("吃的");
			
			Goods gs = new Goods();
			gs.setGname("奶茶");
			gs.setPrice("5");
			gs.setGdesc("喝了会长胖");
		    
			
			//2 在分类表示所有商品，在商品表示分类		
			// 建立分类对象和商品对象关系
			//2.1 把商品对象 放到分类对象的set集合里面
			cy.getSetGoods().add(gs);
			//2.2 把分类对象放到商品里面
			gs.setClassify(cy);
			
			//3 保存到数据库
			session.save(gs);
			session.save(cy);
			
			//提交事务
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory不需要关闭
			sessionFactory.close();
		}
	}
}


