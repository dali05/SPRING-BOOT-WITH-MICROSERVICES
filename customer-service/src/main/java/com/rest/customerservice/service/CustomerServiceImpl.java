package com.rest.customerservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.customerservice.dao.CustomerDao;
import com.rest.customerservice.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public String createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String msg = null;
		try {
			msg = customerDao.createCustomer(customer);
			logger.info("Successfully created Customer");
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while creating Customer");
			msg = "Error";
		}
		return msg;
	}

	@Override
	public Customer getCustomer(String id) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			customer = customerDao.getCustomer(id);
			logger.info("Getting Customer Entity for id {} - {}",id,customer);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while getting Customer for id{}:",id);
			customer = null;
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		List<Customer> customerList = null;
		try {
			customerList = customerDao.getCustomerList();
			logger.info("Getting Customer List = {} ",customerList);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while getting Customer List");
		}
		return customerList;
	}

	@Override
	public Customer updateCustomer(Customer customer, String id) {
		// TODO Auto-generated method stub
		Customer updatedCustomer = null;
		try {
			updatedCustomer = customerDao.updateCustomer(customer, id);
			logger.info("Getting updated Customer Entity: {}", updatedCustomer);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while updating Customer Entity");
		}
		return updatedCustomer;
	}

	@Override
	public String removeCustomer(String id) {
		// TODO Auto-generated method stub
		String msg = null;
		try {
			msg = customerDao.removeCustomer(id);
			logger.info("Customer removed successfully");
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while removing Customer");
			msg = "Error";
		}
		return msg;
	}
}
