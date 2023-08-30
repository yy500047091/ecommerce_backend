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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.Category;
import com.app.service.ICategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService catService;
	
	public CategoryController() {
		System.out.println("in ctor of " + getClass());
	}
	
	@GetMapping
	public List<Category> listOfCategory() {
		System.out.println("in list prods");
		return catService.getAllCategories();
	}
	
	@PostMapping
	public ResponseEntity<?> addCateDetails(@RequestBody  Category transientCate) {
		System.out.println("in add dtls " + transientCate);
		try {
			// invoke service layer method
			return new ResponseEntity<>(catService.addCategory(transientCate)	, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add prod " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCateDetails(@PathVariable Long id) {
		System.out.println("in del prod " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(catService.deleteCategory(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  prod " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Emp ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);// =>
																													// invalid
																													// prod
																													// id
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetails(@PathVariable  Long id) {
		System.out.println("in get prod " + id);
			return ResponseEntity.ok(catService.getCatDetails(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCateDetails(@RequestBody Category detachedCate) {

		System.out.println("in update prod " + detachedCate);
		try {
			return ResponseEntity.ok(catService.updateCateDetails(detachedCate));
		} catch (RuntimeException e) {
			System.out.println("err in update  prod " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}


}
