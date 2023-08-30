package com.app.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartRepository;
import com.app.dao.ProductRepository;
import com.app.dao.UserRepository;
import com.app.entities.Cart;
import com.app.entities.Product;
import com.app.entities.User;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public String deleteFromCart(Long id) {
		cartRepo.deleteById(id);
		return "Cart Deleted Successfully: " + id;
	}

	@Override
	public List<Cart> getAllCartDetails() {
		return cartRepo.findAll();
	}

	@Override
	public Cart getCartDetails(Long id) {
		return cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Cart ID: " + id));
	}

	@Override
	public Cart updateCart(Cart detachedCart, Long productId, Long userId) {
		Product product = prodRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID: " + productId));
		detachedCart.setProduct(product);

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User ID: " + userId));
		detachedCart.setUser(user);
		detachedCart.setOrdered_date(new Date());

		if (cartRepo.existsById(detachedCart.getId())) {
			return cartRepo.save(detachedCart);
		}

		throw new ResourceNotFoundException("Invalid Cart ID: Updation Failed: " + detachedCart.getId());
	}

	@Override
	public Cart addToCart(Cart transientCart, Long productId, Long userId) {
		Product product = prodRepo.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Product ID: " + productId));
		transientCart.setProduct(product);

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User ID: " + userId));
		transientCart.setUser(user);
		   transientCart.setOrdered_date(new Date());

		return cartRepo.save(transientCart);
	}


}
