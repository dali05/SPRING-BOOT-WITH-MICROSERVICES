package com.rest.productservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.productservice.model.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{

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
	public String createProduct(Product product) {
		// TODO Auto-generated method stub
		String msg = "";
		try {
			if(product != null) {
				em.persist(product);
				msg = "Success";
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg = "Error";
			e.printStackTrace();
		}
//		sessionFactory.getCurrentSession().save(product);
		return msg;
	}


	@Override
	public Product getProduct(String id) {
		// TODO Auto-generated method stub
		Product product = null;
		try {
			String query = "Select t from Product t where t.id = "+id;
			product = em.createQuery(query,Product.class).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			product = null;
			e.printStackTrace();
		}
		return product;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		try {
			String query = "Select t.* from Product t";
			productList = em.createNativeQuery(query,Product.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return productList;
	}


	@Override
	public Product updateProduct(Product product, String id) {
		// TODO Auto-generated method stub
		Product updatedProduct = null;
		try {
			Product oldProduct = em.find(Product.class, id);
			oldProduct.setName(product.getName());
			updatedProduct = em.merge(oldProduct);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return updatedProduct;
	}


	@Override
	public String removeProduct(String id) {
		// TODO Auto-generated method stub
		String msg = "";
		try {
			Product oldProduct = em.find(Product.class, id);
			em.remove(em.merge(oldProduct));
			msg = "Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "Error";
		}
		
		return msg;
	}

}
