package com.binarylogicit.address.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binarylogicit.address.entity.Address;
import com.binarylogicit.address.repository.AddressRepository;
import com.binarylogicit.address.request.AddressRequest;
import com.binarylogicit.address.response.AddressResponse;

@Service
public class AddressService {

Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressRepository addressRepository;

	public AddressResponse createAddress(AddressRequest request) {
		
		Address address = new Address();
		address.setStreet(request.getStreet());
		address.setCity(request.getCity());
		
		addressRepository.save(address);
		
		return new AddressResponse(address);
		
	}
	
	public AddressResponse getById (long id) {
		
		logger.info("Inside getById " + id);
		
		Address address = addressRepository.findById(id).get();
		
		return new AddressResponse(address);
	}

}
