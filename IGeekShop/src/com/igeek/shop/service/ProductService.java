package com.igeek.shop.service;

import java.sql.SQLException;
import java.util.List;

import com.igeek.shop.dao.ProductDao;
import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.PageBean;
import com.igeek.shop.entity.Product;

public class ProductService {

	ProductDao dao=new ProductDao();
	
	public List<Product> findHotProductList() {
		// TODO Auto-generated method stub
		List<Product> hotProductList=null;
		
		try {
			hotProductList=dao.findHotProductList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hotProductList;
	}

	public List<Product> findNewProductList() {
		// TODO Auto-generated method stub
		List<Product> newProductList=null;
		
		try {
			newProductList=dao.findNewProductList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newProductList;
	}

	public List<Category> findCategoryList(){
		List<Category>categoryList=null;
		
		try {
			categoryList=dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
		
	}

	public PageBean<Product> findProductListByCid(String cid,int currentPage,int currentCount) {
		// TODO Auto-generated method stub
		PageBean<Product>pageBean=new PageBean<Product>();
		
		//��ǰҳ
		//int currentPage=1;
		pageBean.setCurrentPage(currentPage);
		//int currentCount=12;
		pageBean.setCurrentCount(currentCount);
		int totalCount=0;
		try {
			totalCount=dao.getCount(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		int totalPage=(int)Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		int start=(currentPage-1)*currentCount;
		List<Product>list=null;
		try {
			list=dao.findProductListByPage(cid, start, currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	
}
