package com.littlecat.dao;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.Orderitem;
@Repository("orderItemDao")
public interface OrderitemMapper {
    int deleteByPrimaryKey(String itemid);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    Orderitem selectByPrimaryKey(String itemid);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}