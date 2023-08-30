package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.dao.ProductRepository;
import com.app.dao.UserRepository;
import com.app.entities.Category;
import com.app.entities.Product;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
@Autowired
private ProductRepository prodRepo;
@Autowired
private UserRepository userRepo;

@Autowired
private ModelMapper mapper;

@Autowired
private CategoryRepository catRepo;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		
		return prodRepo.findAll();
	}

	@Override
	public Product addProd(Product transientPro,Long id) {
		

		Category  cat = catRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product id !!!!!!!"));
		transientPro.setCategory(cat);	
		return prodRepo.save(transientPro);
		
	}
	

	@Override
	public String deleteProd(Long id) {
		// TODO Auto-generated method stub
		prodRepo.deleteById(id);
		return "Prod details deleted for prod id " + id;
	}

	@Override
	public Product getProdDetails(Long id) {
		// TODO Auto-generated method stub
		
		return prodRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID " + id));
		
	}

	@Override
	public Product updateProdDetails(Product detachedProd,Long id) {
		
		Product product=prodRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Product id !!!!!!!"));
		if(product!=null) {
		return prodRepo.save(detachedProd);
		}
		throw new ResourceNotFoundException("Invalid Prod ID : Updation Failed !!!!!!!!!" + detachedProd.getId());	
		}

//	@Override
//	public Product updateProdDetails(ProductDTO detachedProd) {
//		if (prodRepo.existsById(detachedProd.getId()))
//			return prodRepo.save(detachedProd);//update
//		throw new ResourceNotFoundException("Invalid Prod ID : Updation Failed !!!!!!!!!" + detachedProd.getId());
//	
//	}
	

}
