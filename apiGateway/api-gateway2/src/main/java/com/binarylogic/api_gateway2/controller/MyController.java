package com.binarylogic.api_gateway2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class MyController {

	  @GetMapping("/greet")
	    public String greet() {
	        return "Welcome to API gateway";
	    }
	
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}