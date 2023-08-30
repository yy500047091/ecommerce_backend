package com.app.service;

import com.app.entities.Address;

public interface IAddressService {
	
	Address addAddress(Address transientAddre,Long id);

	String deleteAddress(Long addressId);

	Address getAddDetails(Long addressId);

	Address updateAddressDetails(Address detachedAddress,Long id);

	

}
