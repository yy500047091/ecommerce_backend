package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dto.ProductDTO;
import com.app.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

	boolean existsById(Long id);

	ProductDTO save(ProductDTO transientPro);
	
	boolean findByProductName(String productName);

	
}
 