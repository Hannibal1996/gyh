package com.littlecat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.littlecat.dao.CategoryMapper;
import com.littlecat.entity.Category;
import com.littlecat.service.CategoryService;
 
@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {
	@Resource(name = "categoryDao")
	private CategoryMapper categoryDao;
	
	@Override
	public List<Category> findCategoryList() {
		// TODO Auto-generated method stub
		return categoryDao.findCategoryList();
	}

}
