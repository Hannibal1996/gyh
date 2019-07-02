package com.littlecat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.Orders;
@Repository("ordersDao")
public interface OrdersMapper {
    int deleteByPrimaryKey(String oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKeyWithBLOBs(Orders record);

    int updateByPrimaryKey(Orders record);
    List<Orders> findAllOrders(String uid);

	List<Orders> findNoPay(String uid);

	int updateOrders(String oid);
}