package com.ribbon.ribbonclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ribbon.ribbonclient.feignclient.CustomerService;
import com.ribbon.ribbonclient.model.Product;

@RestController
@RequestMapping("/")
//@CrossOrigin(origins = "http://localhost:9090")
public class BaseController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@HystrixCommand(fallbackMethod = "defaultFallbackGetProducts")
	private ResponseEntity<Object> getProducts() {
		return customerService.getProducts();
//		return new RestTemplate().getForObject("http://localhost:8080/customer-service/restTemplate/getProducts", Object[].class);
	}
	
	@RequestMapping(value = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable("id") final String id){
		return customerService.getProductById(id);
	}
	
//	private ResponseEntity<Object> defaultFallbackGetProducts(){
//		return new ResponseEntity<>("Service Unavailable..! Server is currently unable to handle the request due to a temporary overloading or maintenance of the server",HttpStatus.SERVICE_UNAVAILABLE);
//	}
}
