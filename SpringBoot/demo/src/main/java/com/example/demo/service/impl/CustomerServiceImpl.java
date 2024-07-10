package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.CustomerResponse;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.feignclients.AddressFeignClient;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository repository;

	@Autowired
	AddressFeignClient addressFeignClient; 
	
	@Override
	public CustomerResponse findCustomerById(int id) {
		Optional<Customer> cust= repository.findById(id);
		if(cust.isPresent()) {
			CustomerResponse response= new CustomerResponse(cust.get());
			response.setAddressResponse(addressFeignClient.getById(cust.get().getAddressId()));
			return new CustomerResponse(cust.get());
		}else {
			throw new CustomerNotFoundException("Customer is not avaiable with the given ID : "+ id);
		}
	}

	/*
	 * @Override public List<Customer> findCustomerByFirstName(String firstName) {
	 * return repository.findByFirstName(firstName); }
	 */
	
	@Override
	public List<Customer> findCustomerByLastName(String lastName) {
		List<Customer> list= repository.findByLastName(lastName);
		if(!list.isEmpty()) {
			return list;
		}else {
			throw new CustomerNotFoundException("Customer is not avaiable with the given Last Name : "+ lastName);
		}
		
	}



	@Override
	public CustomerResponse saveCustomer(Customer customer) {
		if(customer.getFirstName().isBlank() || customer.getFirstName().isEmpty() || customer.getFirstName()==null) {
			throw new CustomerNotFoundException("Customer can not be saved in to the system");
		}else {
			Customer c=new Customer();
			c.setFirstName(customer.getFirstName());
			c.setLastName(customer.getLastName());
			c.setProduct(customer.getProduct());
			c.setAddressId(customer.getAddressId());
			c = repository.save(customer);
			CustomerResponse response= new CustomerResponse(c);
			response.setAddressResponse(addressFeignClient.getById(c.getAddressId()));
			return response;
		}
		
	}

	@Override
	public String deleteCustomerById(int id) {
		
		Optional<Customer> customer = repository.findById(id);
        if(customer!=null){
            repository.deleteById(id);
            return  "Customer Deleted Successfully";
        }else{
            throw new CustomerNotFoundException("Customer Id : " + id +  " does not exit ");
        }
		
	}

	public List<Customer> findAllCustomer() {
		Iterable<Customer> customers =repository.findAll();
		List<Customer> list = new ArrayList<>();
		customers.forEach(list::add);
		return list;
		
	}

	@Override
	public Customer updateCustomer(int id, Customer customer) {

		Optional<Customer> c = repository.findById(id);
		 
       if(c!=null) {
    	   	Customer cust = c.get();
         	if(customer.getFirstName() != null)
         		cust.setFirstName(customer.getFirstName());
         	if(customer.getLastName() != null)
         		cust.setLastName(customer.getLastName());
           
            if(customer.getProduct() != null)
            	cust.setProduct(customer.getProduct());

            	repository.save(cust);
            	return  cust;
       }
        else{
            throw new CustomerNotFoundException("Customer with given id: " + id +  " is not available !!");
        }
	}

}
