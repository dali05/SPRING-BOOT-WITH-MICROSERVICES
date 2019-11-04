package com.rest.productservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.productservice.dao.ProductDao;
import com.rest.productservice.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public String createProduct(Product product) {
		// TODO Auto-generated method stub
		String msg = null;
		try {
			msg = productDao.createProduct(product);
			logger.info("Successfully created product");
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while creating product");
			msg = "Error";
		}
		return msg;
	}

	@Override
	public Product getProduct(String id) {
		// TODO Auto-generated method stub
		Product product = null;
		try {
			product = productDao.getProduct(id);
			logger.info("Getting Product Entity for id {} - {}",id,product);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while getting Product for id{}:",id);
			product = null;
		}
		return product;
	}

	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		List<Product> productList = null;
		try {
			productList = productDao.getProductList();
			logger.info("Getting Product List = {} ",productList);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while getting Product List");
		}
		return productList;
	}

	@Override
	public Product updateProduct(Product product, String id) {
		// TODO Auto-generated method stub
		Product updatedProduct = null;
		try {
			updatedProduct = productDao.updateProduct(product, id);
			logger.info("Getting updated Product Entity: {}", updatedProduct);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while updating Product Entity");
		}
		return updatedProduct;
	}

	@Override
	public String removeProduct(String id) {
		// TODO Auto-generated method stub
		String msg = null;
		try {
			msg = productDao.removeProduct(id);
			logger.info("Product removed successfully");
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			logger.error("Getting exception while removing product");
			msg = "Error";
		}
		return msg;
	}
	
}
