package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerResponse;
import com.example.demo.model.Customer;

public interface CustomerService {
	
	public CustomerResponse findCustomerById(int id);
	
	public List<Customer> findCustomerByLastName(String lastName) ;
	
	//public List<Customer> findCustomerByFirstName(String firstName);
	
	public CustomerResponse saveCustomer(Customer customer) ;
	
	public String deleteCustomerById(int id) ;

	public List<Customer> findAllCustomer();

	public Customer updateCustomer(int id, Customer customer);
	
}
