package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entities.User;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {

		return userRepo.findAll();
	}

	@Override
	public User addUser(User transientUser) {

		return userRepo.save(transientUser);
	}

	@Override
	public User updateUserDetails(User detachedUser) {
		if (userRepo.existsById(detachedUser.getId()))
			return userRepo.save(detachedUser);//update
		throw new ResourceNotFoundException("Invalid Prod ID : Updation Failed !!!!!!!!!" + detachedUser.getId());
	
	}

	@Override
	public String deleteAccount(Long id) {
		userRepo.deleteById(id);
		return "Deleted User Succesfully " + id;
	}

	@Override
	public User getUserDetails(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Prod ID " + id));

	}

	@Override
	public Optional<User> authenticateUser(String email, String password) {
		User u = userRepo.findByEmailAndPassword(email, password).orElseThrow(()-> new RuntimeException("Authentication Failed"));
		return  userRepo.findById(u.getId());
		
	}

}
