package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Cart;
import com.app.entities.Product;

public interface CartRepository  extends JpaRepository<Cart,Long>
{

	Optional<Product> findByUserId(Long userId);
	

}
