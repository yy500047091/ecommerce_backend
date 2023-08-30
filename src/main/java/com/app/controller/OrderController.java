package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.Orders;
import com.app.service.IOrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	
	public OrderController() {
		System.out.println("in ctor of " + getClass());
	}
	
	@GetMapping
	private List<Orders> listOrders(){
		System.out.println("Orders List");
		return orderService.getAllOrders();
		
	}
	@PostMapping
	public ResponseEntity<?> addOrderDetails(@RequestBody  Orders transientOrder,@RequestParam Long id,@RequestParam(name= "id") Long id1) {
		System.out.println("in add dtls " + transientOrder);
		try {
			// invoke service layer method
			return new ResponseEntity<>(orderService.addOrders(transientOrder,id,id1), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable Long id) {
		System.out.println("in del order " + id );
		try {
			return ResponseEntity.ok(new ApiResponse(orderService.deleteOrder(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Order ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);// =>
																													// invalid																													// emp																												// id
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderDetails(@PathVariable  Long id) {
		System.out.println("in get orders " + id);
				return ResponseEntity.ok(orderService.getOrderDetails(id));
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateOrderDetails(@RequestBody Orders detachedOrder,@RequestParam Long id,@RequestParam(name="id") Long id1) {

		System.out.println("in update prod " + detachedOrder);
		try {
			return ResponseEntity.ok(orderService.updateOrderDetails(detachedOrder,id,id1));
		} catch (RuntimeException e) {
			System.out.println("err in update  emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}


}
