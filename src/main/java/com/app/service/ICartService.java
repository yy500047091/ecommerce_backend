package com.app.service;

import java.util.List;

import com.app.entities.Cart;

public interface ICartService {
    List<Cart> getAllCartDetails();

    Cart getCartDetails(Long id);

   

    

    String deleteFromCart(Long id);

	Cart updateCart(Cart detachedCart, Long productId, Long userId);

	Cart addToCart(Cart transientCart, Long productId, Long userId);

    // Other methods for cart management
}

