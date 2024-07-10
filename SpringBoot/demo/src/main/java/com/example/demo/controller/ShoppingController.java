package com.example.demo.controller;

/**
 /* @author Preeti
 * 
 */
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dto.CustomerResponse;
import com.example.demo.feignclients.AddressFeignClient;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class ShoppingController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ShoppingController.class);


	@GetMapping("/")
	public ResponseEntity<String> greeting() {
		logger.info("Inside greeting method");
		return new ResponseEntity<>("Welcome to our shopping app",HttpStatus.OK);
		
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		logger.info("Inside getCustomers method");
		return new ResponseEntity<>(customerService.findAllCustomer(),HttpStatus.OK);
		
	}
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer) {
		logger.info("Inside saveCustomer method");
		return new ResponseEntity<>(customerService.saveCustomer(customer),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customer/{id}")
	    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
			logger.info("Inside updateCustomer method");
	        return new ResponseEntity<>(customerService.updateCustomer(id,customer),HttpStatus.ACCEPTED);
	    }

	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int id) {
		logger.info("Inside getCustomerById method");
		return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
		
	}

	
	  @GetMapping("/customers/{name}") 
	  public ResponseEntity<List<Customer>> getCustomerByLastName(@PathVariable String name) { 
		  logger.info("Inside getCustomerByLastName method");
		  return new ResponseEntity<>(customerService.findCustomerByLastName(name), HttpStatus.OK);
	  }
	 
	

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable int id) {
		logger.info("Inside deleteCustomerById method");
		 return new ResponseEntity<>(customerService.deleteCustomerById(id), HttpStatus.ACCEPTED);
	}
	
	

	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		logger.info("Inside getProducts method");
		return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
		
	}
	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		logger.info("Inside saveProduct method");
		return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		logger.info("Inside getProductById method");
		return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
		
	}
	

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id) {
		logger.info("Inside deleteProductById method");
		return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
			
			
	}
	 @PutMapping("/product/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
		 logger.info("Inside updateProduct method");
	        return new ResponseEntity<Product>(productService.updateProduct(id,product),HttpStatus.ACCEPTED);
	    }
	
		 
}
