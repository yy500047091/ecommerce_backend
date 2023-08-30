package com.app.dto;

import lombok.Data;

@Data
public class CartItemDTO {
	private Integer id;
    private Long productId;
    private Integer quantity;

    // Constructors, getters, and setters
}

