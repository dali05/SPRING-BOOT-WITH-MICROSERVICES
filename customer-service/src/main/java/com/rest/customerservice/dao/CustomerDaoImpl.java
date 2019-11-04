package com.rest.customerservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.customerservice.model.Customer;


@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	private EntityManager em;
	

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	
	/**
	 * @Transactional if your are using this annotation on method level then in that case if 
	 * we get any exception while persisting data into batches whole transaction gets rollback 
	 * but in case if you are using this annotation on class level then in that case all the data will be saved till exception raised.
	 */
	
	@Override
	public String createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String msg = "";
		try {
			if(customer != null) {
				em.persist(customer);
				msg = "Success";
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg = "Error";
			e.printStackTrace();
		}
//		sessionFactory.getCurrentSession().save(customer);
		return msg;
	}


	@Override
	public Customer getCustomer(String id) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			String query = "Select t from Customer t where t.id = "+id;
			customer = em.createQuery(query,Customer.class).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			customer = null;
			e.printStackTrace();
		}
		return customer;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		List<Customer> customerList = null;
		try {
			String query = "Select t.* from Customer t";
			customerList = em.createNativeQuery(query,Customer.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return customerList;
	}


	@Override
	public Customer updateCustomer(Customer customer, String id) {
		// TODO Auto-generated method stub
		Customer updatedCustomer = null;
		try {
			Customer oldCustomer = em.find(Customer.class, id);
			oldCustomer.setName(customer.getName());
			updatedCustomer = em.merge(oldCustomer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return updatedCustomer;
	}


	@Override
	public String removeCustomer(String id) {
		// TODO Auto-generated method stub
		String msg = "";
		try {
			Customer oldCustomer = em.find(Customer.class, id);
			em.remove(em.merge(oldCustomer));
			msg = "Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "Error";
		}
		
		return msg;
	}
}
