package com.littlecat.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.littlecat.entity.HistoryRecord;
import com.littlecat.entity.Product;

public interface ProductService {
	public List<Product> findHotProducts(int isHot, int offset, int rows);

	public List<Product> findNewProductList(int offset, int rows);

	public List<Object> findProductListByCid(String cid, int offset, int rows);

	public int productCount(String cid);

	public Product findProductById(String pid);

	public List<Object> findProductListByName(String pname, int offset, Integer rows);//根据name查询

	public int productCountByName(String pname);//查询时计算总数

	public int addHistoryRecored(String pid, String uid);//增加历史记录

	List<HistoryRecord> findByUid(String uid, int offset, int rows);//根据用户id查询历史记录
	public int hrCount(String uid);//查询历史纪录时计算总数

	public int delHistory(String uid);//删除历史记录

}
