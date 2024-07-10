package com.binarylogicit.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binarylogicit.address.request.AddressRequest;
import com.binarylogicit.address.response.AddressResponse;
import com.binarylogicit.address.service.AddressService;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins ="*")
public class AddressController {

		@Autowired
		AddressService addressService;

		@PostMapping("/create")
		public AddressResponse createAddress (@RequestBody AddressRequest request) {
			return addressService.createAddress(request);
		}
		
		@GetMapping("/getById/{id}")
		public AddressResponse getById(@PathVariable long id) {
			return addressService.getById(id);
		}
	
}

