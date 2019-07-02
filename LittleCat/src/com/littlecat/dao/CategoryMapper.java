package com.littlecat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.Category;
 
@Repository("categoryDao")
public interface CategoryMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<Category> findCategoryList() ;
}