package com.littlecat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.littlecat.dao.OrderitemMapper;
import com.littlecat.dao.OrdersMapper;
import com.littlecat.entity.Orderitem;
import com.littlecat.entity.Orders;
import com.littlecat.service.OrdersService;

@Service("ordersServiceimpl")
public class OrdersServiceimpl implements OrdersService {
	@Resource(name = "ordersDao")
	private OrdersMapper ordersDao;
	@Resource(name = "orderItemDao")
	private OrderitemMapper ordersItemDao;
	@Override
	public int addOrders(Orders orders) {
		// TODO Auto-generated method stub
		return ordersDao.insertSelective(orders);
	}
	@Override
	public int addOrdersItem(Orderitem orderItem) {
		// TODO Auto-generated method stub
		return ordersItemDao.insertSelective(orderItem);
	}
	@Override
	public List<Orders> findAllOrders(String uid) {
		// TODO Auto-generated method stub
		return ordersDao.findAllOrders(  uid);
	}
	@Override
	public List<Orders> findNoPay(String uid) {
		// TODO Auto-generated method stub
		return ordersDao.findNoPay(  uid); 
	}
	@Override
	public int delOrder(String oid) {
		// TODO Auto-generated method stub
		return ordersDao.deleteByPrimaryKey(oid);
	}
	@Override
	public int updateOrders(String oid) {
		// TODO Auto-generated method stub
		return ordersDao.updateOrders(oid);
	}
	@Override
	public Orders findOrdersByOID(String oid) {
		// TODO Auto-generated method stub
		return ordersDao.selectByPrimaryKey(oid);
	}

}
