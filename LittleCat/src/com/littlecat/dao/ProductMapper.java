package com.littlecat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.littlecat.entity.Product;

 
@Repository("productDao")
public interface ProductMapper {
	int deleteByPrimaryKey(String pid);

	int insert(Product record);

	int insertSelective(Product record);

	Product selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);

	List<Product> findHotProducts(@Param("isHot") int isHot, @Param("offset") int offset, @Param("rows") int rows);

	List<Product> findNewProductList(@Param("offset") int offset, @Param("rows") int rows);

	List<Object> findProductListByPage(String cid, int offset, int rows);

	int productCount(String cid);

	List<Object> findProductListByName(@Param("pname")String pname,@Param("offset") int offset, @Param("rows")int rows);

	int productCountByName(@Param("pname") String pname);
}