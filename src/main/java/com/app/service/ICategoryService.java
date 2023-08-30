package com.app.service;

import java.util.List;

import com.app.entities.Category;

public interface ICategoryService {

	List<Category> getAllCategories();


	String deleteCategory(Long categoryId);

	
	Category updateCateDetails(Category detachedCate);
	Category addCategory(Category transientCategory);


	Category getCatDetails(Long id);
}
