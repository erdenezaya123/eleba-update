package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Orders;

public interface OrdersService {

	public int createOrders(Orders orders);
	public Orders getOrdersById(Integer orderId);
	public List<Orders> listOrdersByUserId(String userId);

	void pay(Integer orderId);
	public int turnOrderState(Integer orderId);
	//public void deleteOrder(Integer orderId);
}
