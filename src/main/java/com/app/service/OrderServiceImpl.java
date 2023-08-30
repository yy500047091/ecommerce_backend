package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AddressRepository;
import com.app.dao.OrderRepository;
import com.app.dao.UserRepository;
import com.app.entities.Address;
import com.app.entities.Orders;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private AddressRepository addRepo;
	
	@Autowired 
	private UserRepository userRepo;
	
	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Orders addOrders(Orders transientOrder, Long addressid, Long userId) {

		Address a = addRepo.findById(addressid).orElseThrow(() -> new ResourceNotFoundException("Invalid Address id !!!!!!!"));
		transientOrder.setAddress(a);
		
//		User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User id !!!!!!!"));	
//		transientOrder.setUser(u);
		
		return orderRepo.save(transientOrder);
		
	}

	@Override
	public String deleteOrder(Long id) {
		orderRepo.deleteById(id);
		return "Deleted SuccesFully " + id;
		
	}

	@Override
	public Orders getOrderDetails(Long id) {
		
		return orderRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Address ID " + id));
		
	}

	@Override
	public Orders updateOrderDetails(Orders detachedOrder, Long addressid, Long userId) {
		
		if(addRepo.existsById(detachedOrder.getId())) {
		Address a = addRepo.findById(addressid).orElseThrow(() -> new ResourceNotFoundException("Invalid Address id !!!!!!!"));
		detachedOrder.setAddress(a);
		
//		User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User id !!!!!!!"));	
//		detachedOrder.setUser(u);
		
		return orderRepo.save(detachedOrder);
	
		}
		throw new ResourceNotFoundException("Invalid Address ID : Updation Failed !!!!!!!!!" + detachedOrder.getId());
		
		
	}

//	@Override
//	public Orders addOrders(Orders transientOrder, Long addressid) {
//		Address a = addRepo.findById(addressid).orElseThrow(() -> new ResourceNotFoundException("Invalid Address id !!!!!!!"));
//		transientOrder.setAddress(a);
//		return orderRepo.save(transientOrder);
//	}
	

}
