package com.app.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.Address;
import com.app.service.IAddressService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/address")
public class AddressController {
	public AddressController() {

		System.out.println("in ctor of " + getClass());
	}

	@Autowired
	private IAddressService addService;
	
	@PostMapping
	public ResponseEntity<?> addAddressDetails(@RequestBody  Address transientAdd,@RequestParam Long id) {
		System.out.println("in add dtls " + transientAdd);
		try {
			// invoke service layer method
			return new ResponseEntity<>(addService.addAddress(transientAdd,id)	, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddressDetails(@PathVariable Long id) {
		System.out.println("in del Address " +id );
		try {
			return ResponseEntity.ok(new ApiResponse(addService.deleteAddress(id)));
		} catch (RuntimeException e) {
			System.out.println("err in del  Address " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Address ID !!!!!!!!!!!!!!!!"), HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressDetails(@PathVariable  Long id) {
		System.out.println("in get Address " + id);
	//	try {
			return ResponseEntity.ok(addService.getAddDetails(id));
//		} catch (RuntimeException e) {
//			System.out.println("err in get  Address " + e);
//			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);// =>
//																								// invalid
//																								// Address
//																								// id
//		}
	}
	
	// add REST API endpoint to update existing Address details
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAddressDetails(@RequestBody Address detachedAdd,@RequestParam Long id) {

		System.out.println("in update prod " + detachedAdd);
		try {
			return ResponseEntity.ok(addService.updateAddressDetails(detachedAdd,id));
		} catch (RuntimeException e) {
			System.out.println("err in update  Address " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);// =>
																								// invalid
																								// Address
																								// id
		}
	}

}
