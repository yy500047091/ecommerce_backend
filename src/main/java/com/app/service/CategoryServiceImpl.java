package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.entities.Category;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Category> getAllCategories() {
		return catRepo.findAll();
	}

	@Override
	public String deleteCategory(Long id) {
		catRepo.deleteById(id);
		return "Category deleted Succesfully " + id ;
	}

	@Override
	public Category getCatDetails(Long id) {
		
		return catRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Address ID " + id));
		
	}

	@Override
	public Category updateCateDetails(Category detachedCate) {
		
		if(catRepo.existsById(detachedCate.getId())) {
		return catRepo.save(detachedCate);
	}
		
		throw new ResourceNotFoundException("Invalid Address ID : Updation Failed !!!!!!!!!" + detachedCate.getId());		
	}

	@Override
	public Category addCategory(Category transientCategory) {
		return catRepo.save(transientCategory);
	}

}
