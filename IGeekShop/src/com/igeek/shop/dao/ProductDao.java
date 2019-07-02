package com.igeek.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.igeek.common.utils.DataSourceUtils;
import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.Product;

public class ProductDao {

	/*
	 * 查询热门商品
	 */
	public List<Product> findHotProductList() throws SQLException{
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where is_hot = ? limit ?,?";
		
		
		return runner.query(sql, new BeanListHandler<Product>(Product.class), 1,0,9);
		
	}
	
	/*
	 * 查询最新商品
	 */
	public List<Product> findNewProductList() throws SQLException{
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql=" select * from product order by pdate desc limit ?,?";
		
		return runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		
	}
	
	/*
	 * 查询分类导航
	 */
	public List<Category> findAllCategory() throws SQLException{
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select *from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}
	/*
	 * cid:类别ID
	 */
	public int getCount(String cid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product where cid=?";
		Long row=(Long)runner.query(sql, new ScalarHandler(), cid);
		return row.intValue();
	}
	/*
	 * 根据类别ID查找的页码检索相应的商品列表
	 */
	public List<Product>findProductListByPage(String cid,int start,int count) throws SQLException{
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where cid=? limit ?,?";
		
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid,start,count);
		
	}
	
}
