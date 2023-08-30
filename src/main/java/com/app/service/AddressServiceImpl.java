package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AddressRepository;
import com.app.dao.UserRepository;
import com.app.entities.Address;
import com.app.entities.User;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository addRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Address addAddress(Address transientAddre,Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid User id !!!!!!!"));
		transientAddre.setUser(u);
		return addRepo.save(transientAddre);
	}

	@Override
	public String deleteAddress(Long addressId) {
		addRepo.deleteById(addressId);
		return "address deleted succesfull for " + addressId;
	}

	@Override
	public Address getAddDetails(Long addressId) {
		// TODO Auto-generated method stub
		return addRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Invalid Address ID " + addressId));
		

	}

	@Override
	public Address updateAddressDetails(Address detachedAddress,Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid User id !!!!!!!"));
			if(addRepo.existsById(detachedAddress.getId())) {
				detachedAddress.setUser(u);		
			return addRepo.save(detachedAddress);
		}
			
			throw new ResourceNotFoundException("Invalid Address ID : Updation Failed !!!!!!!!!" + detachedAddress.getId());
			
	}

}
