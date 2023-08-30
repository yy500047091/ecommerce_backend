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
import com.app.entities.User;
import com.app.service.IUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	public UserController() {
	System.out.println("in ctor of " + getClass());
	}
	
	@GetMapping
	private List<User> listofUser(){
		System.out.println("in list Of User");
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<?> addUserDetails(@RequestBody  User transientUser) {
		System.out.println("in add dtls " + transientUser);
		try {
			// invoke service layer method
			return new ResponseEntity<>(userService.addUser(transientUser)	, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable Long id) {
		System.out.println("in del emp " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(userService.deleteAccount(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  emp " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid User ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);// =>
																													// invalid																											// emp																											// id
		}
}
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable  Long id) {
		System.out.println("in get emp " + id);
	//	try {
			return ResponseEntity.ok(userService.getUserDetails(id));
//		} catch (RuntimeException e) {
//			System.out.println("err in get  emp " + e);
//			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
//		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserDetails(@RequestBody User detachedUser) {

		System.out.println("in update prod " + detachedUser);
		try {
			return ResponseEntity.ok(userService.updateUserDetails(detachedUser));
		} catch (RuntimeException e) {
			System.out.println("err in update  emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody User detachedUser)
	{
		try {
			return ResponseEntity.ok(userService.authenticateUser(detachedUser.getEmail(), detachedUser.getPassword()));
		}catch(RuntimeException e)
		{
			System.out.println("err in autheicate user " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
	
	
}
