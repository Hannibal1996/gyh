package com.littlecat.service;

import java.util.List;

import com.littlecat.entity.Orderitem;
import com.littlecat.entity.Orders;

public interface OrdersService {
 public int addOrders(Orders orders);
 
 public int addOrdersItem(Orderitem orderItem);
 public List<Orders> findAllOrders(String uid);
 public List<Orders> findNoPay(String uid);
 public int delOrder(String oid);

public int updateOrders(String oid);

public Orders findOrdersByOID(String oid);
}
