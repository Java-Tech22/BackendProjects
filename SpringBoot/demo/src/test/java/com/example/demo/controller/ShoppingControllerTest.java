package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CustomerResponse;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ShoppingControllerTest {




	@InjectMocks
	private ShoppingController shoppingController;
	@Mock
	private CustomerServiceImpl customerService;


	@Test
	@DisplayName("Retrieving All Customers")
	public void customerController_getCustomers_success(){
		
		
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("Ana");
		customer.setLastName("Dalvi");
		customer.setProduct(Stream.of( new Product(1, "TV", "Smart TV", null, null, 0, customer)).collect(Collectors.toList()));
		customers.add(customer);

	
		Mockito.when(customerService.findAllCustomer()).thenReturn(customers);
		ResponseEntity<List<Customer>> result = shoppingController.getCustomers();

		Assertions.assertEquals(1,result.getBody().size());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

	}


	@Test
	@DisplayName("Get Customer by ID success test case")
	public void shoppingController_getCustomerById_success(){

		Customer customer = new Customer();
		customer.setId(1);

		int expectedCustomerID = 1;

		CustomerResponse response= new CustomerResponse(customer);
		Mockito.when(customerService.findCustomerById(expectedCustomerID)).thenReturn(response);

		ResponseEntity<CustomerResponse> result = shoppingController.getCustomerById(expectedCustomerID);

		Assertions.assertEquals(expectedCustomerID, result.getBody().getId());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

	}


	@Test
	@DisplayName("Create Customer success test case")
	public void shoppingController_saveCustomer_success(){
		
		Customer customer = new Customer();
		customer.setId(2);
		customer.setFirstName("Anita");
		customer.setLastName("Dev");
		customer.setProduct(Stream.of( new Product(2, "Phone", "Smart Phone", null, null, 0, customer)).collect(Collectors.toList()));
		CustomerResponse response= new CustomerResponse(customer);
		Mockito.when(customerService.saveCustomer(Mockito.any())).thenReturn(response);

		ResponseEntity<CustomerResponse> result = shoppingController.saveCustomer(customer);


		Assertions.assertEquals(customer.getId(), result.getBody().getId());
		Assertions.assertNotNull(result.getBody().getId());
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}


	@Test
	@DisplayName("Shopping controller update Customer success test case")
	public void shoppingController_updateCustomer_success(){

		Customer customer = new Customer();
		customer.setId(2);
		customer.setFirstName("Anita");
		customer.setLastName("Dev");

		int CustomerIdThatNeedToBeUpdated = 2;

		Customer updatedCustomer = new Customer();
		updatedCustomer.setId(CustomerIdThatNeedToBeUpdated);
		updatedCustomer.setFirstName(customer.getFirstName());
		updatedCustomer.setLastName(customer.getLastName());

		Mockito.when(customerService.updateCustomer(CustomerIdThatNeedToBeUpdated, customer)).thenReturn(updatedCustomer);
		ResponseEntity<Customer> result = shoppingController.updateCustomer(CustomerIdThatNeedToBeUpdated, customer);

		Assertions.assertEquals(updatedCustomer.getFirstName(), result.getBody().getFirstName());
		Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		Assertions.assertEquals(CustomerIdThatNeedToBeUpdated, result.getBody().getId());

	}


	@Test
	@DisplayName("Delete Customer By Id success test case")
	public void  shoppingController_deleteCustomerById_success(){
		int customerId = 1;

		Mockito.when(customerService.deleteCustomerById(customerId)).thenReturn("User deleted");

		ResponseEntity<String> result = shoppingController.deleteCustomerById(customerId);

		Assertions.assertEquals("User deleted", result.getBody());
		Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());


	}












}
