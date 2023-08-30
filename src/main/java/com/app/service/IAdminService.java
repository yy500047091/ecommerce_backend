package com.app.service;

import java.util.List;

import com.app.entities.Product;

public interface IAdminService  {
	List<Product> getAllProducts();
	//ProductDTO addProd(ProductDTO transientPro);

	String deleteProd(Long id);

	Product getProdDetails(Long id);

	//Product updateProdDetails(ProductDTO detachedProd);
	//Product addProd(ProductDTO transientPro);

	//Product updateProdDetails(Product product);

	//Product addProd(Product transientPro);

	Product addProd(Product product,Long id);

	Product updateProdDetails(Product detachedProd, Long id);


}
