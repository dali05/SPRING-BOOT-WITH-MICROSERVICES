package com.ribbon.ribbonclient.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ribbon.ribbonclient.model.Product;


@FeignClient(name = "customer-service",  url = "http://localhost:8080/customer-service")
//@FeignClient(name = "customer-service",  url = "http://localhost:8080/customer-service/restTemplate")
//@FeignClient(value = "customer-service", url = "http://localhost:8080")
public interface CustomerService {

//	@RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
//	public String handleRequest();
	
	@RequestMapping(value = "/restTemplate/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/customer-service/restTemplate/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts();
	
	@RequestMapping(value = "/restTemplate/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/customer-service/restTemplate/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable("id") final String id);
}
