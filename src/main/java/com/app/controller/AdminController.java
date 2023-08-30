	package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.entities.Product;
import com.app.service.IAdminService;
import com.app.service.IStorageService;;


@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {
	
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IStorageService storageService;
	
	
	public AdminController() {
		// TODO Auto-generated constructor stub
		System.out.println("in ctor of " + getClass());

	}
	
	
	@GetMapping
	public List<Product> listProducts() {
		System.out.println("in list prods");
		return adminService.getAllProducts();
	}
	


	
	@PostMapping
	public ResponseEntity<?> addProductDetails( ProductDTO transientProd,@RequestParam Long fk_category_id) {
		System.out.println("in add dtls " + transientProd);
		try {
			Product product = this.mapper.map(transientProd, Product.class);
			String thumbnail1 = storageService.store(transientProd.getThumbnail());
			product.setThumbnail(thumbnail1);
			adminService.addProd(product,fk_category_id);
			return com.app.dto.Response.success(product);
					
		} catch (RuntimeException e) {
			System.out.println("err in add prod " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProductDetails(@PathVariable Long id) {
		System.out.println("in del prod " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(adminService.deleteProd(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  prod " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Emp ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);// =>
																													// invalid
																													// prod
																													// id
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetails(@PathVariable  Long id) {
		System.out.println("in get prod " + id);

			return ResponseEntity.ok(adminService.getProdDetails(id));

	}
	
		
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProdDetails(ProductDTO detachedProd,@PathVariable Long id) {

		System.out.println("in update prod " + detachedProd);
		try {
			Product product = this.mapper.map(detachedProd, Product.class);
			String thumbnail1 = storageService.store(detachedProd.getThumbnail());
			product.setThumbnail(thumbnail1);
			adminService.updateProdDetails(product,id);
			return com.app.dto.Response.success(product);

		} catch (RuntimeException e) {
			System.out.println("err in update  prod " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

}
