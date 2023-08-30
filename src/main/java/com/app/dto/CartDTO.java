package com.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
	 private List<CartItemDTO> cartItems;
	private double totalCost;

}
