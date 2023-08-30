package com.app.service;

import java.util.List;

import com.app.entities.Orders;

public interface IOrderService {
	List<Orders> getAllOrders();
	Orders addOrders(Orders transientOrder,Long addressid,Long userId);
	//Orders addOrders(Orders transientOrder,Long addressid);

	String deleteOrder(Long id);
	Orders getOrderDetails(Long id);
	Orders updateOrderDetails(Orders detachedOrder, Long addressid, Long userId);


}
