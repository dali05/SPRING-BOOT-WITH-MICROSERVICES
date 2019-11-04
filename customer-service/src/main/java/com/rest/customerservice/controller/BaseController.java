package com.rest.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.customerservice.model.Customer;
import com.rest.customerservice.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class BaseController {

	@Autowired
	private CustomerService customerService;
	
//	@RequestMapping(value = {"/","/customer"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
//	public String handleRequet() {
//		return "Welcome to customer-service provider";
//	}
	
	@RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String handleRequet() {
		return "Welcome to customer-service provider";
	}

	/***
	 * @param customer
	 * @return ResponseEntity<Object> with HttpStatus
	 */
//	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/createCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//	@RequestMapping(path = "/createCustomer", method = RequestMethod.POST)
	private ResponseEntity<Object> createCustomer(@RequestBody final Customer customer){
		String msg = customerService.createCustomer(customer);
		if(msg != null &&  msg.equals("Success")) {
			return new ResponseEntity<>("Successfully Created..!",HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @param id
	 * @return ResponseEntity<Customer> with HttpStatus
	 */
	@RequestMapping(value = "/getCustomerById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getCustomerById/{id}", method = RequestMethod.GET)
	private ResponseEntity<Customer> getCustomerById(@PathVariable final String id){
		Customer customer = customerService.getCustomer(id);
		if(customer != null) {
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * @return ResponseEntity<List<Customer>> with HttpStatus
	 */
	@RequestMapping(value = "/getCustomers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
	private ResponseEntity<Object> getCustomers(){
		List<Customer> customerList = customerService.getCustomerList();
		if(customerList != null && customerList.size() > 0) {
			return new ResponseEntity<>(customerList,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * @param Customer
	 * @param id
	 * @return ResponseEntity<Customer> with HttpStatus
	 */
	@RequestMapping(value = "/updateCustomer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
//	@RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateCustomer(@RequestBody final Customer customer, @PathVariable final String id){
		Customer updatedCustomer = customerService.updateCustomer(customer, id);
		if(updatedCustomer != null) {
			return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	/**
	 * 
	 * @param id
	 * @return HttpStatus only
	 */
	@RequestMapping(value = "/removeCustomer/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Void> removeCustomer(@PathVariable final String id){
		String msg = customerService.removeCustomer(id);
		if(msg != null && msg.equals("Success")) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * 
	 * @param id
	 * @return HttpStatus only
	 */
	@RequestMapping(value = "/removeCust/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> removeCust(@PathVariable final String id){
		String msg = customerService.removeCustomer(id);
		if(msg != null && msg.equals("Success")) {
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
