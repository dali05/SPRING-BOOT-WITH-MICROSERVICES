package com.rest.customerservice.service;

import java.util.List;

import com.rest.customerservice.model.Customer;


public interface CustomerService {

	public String createCustomer(Customer customer);
	
	public Customer getCustomer(String id);
	
	public List<Customer> getCustomerList();
	
	public Customer updateCustomer(Customer customer, String id);
	
	public String removeCustomer(String id);
}
