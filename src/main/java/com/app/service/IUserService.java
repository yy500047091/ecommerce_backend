package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.User;


public interface IUserService {
	
	List<User> getAllUsers();

	
	public User addUser(User transientUser);
	
	
//	method to edit profile
	public User updateUserDetails(User detachedUser) ;
	
//	method to delete account of user
	public String deleteAccount(Long id);
	
	
//	method to get user details
	public User getUserDetails(Long id);


	Optional<User> authenticateUser(String email, String password);


}
