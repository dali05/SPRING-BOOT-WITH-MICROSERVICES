package com.rest.productservice.service;

import java.util.List;

import com.rest.productservice.model.Product;

public interface ProductService {

	public String createProduct(Product product);
	
	public Product getProduct(String id);
	
	public List<Product> getProductList();
	
	public Product updateProduct(Product product, String id);
	
	public String removeProduct(String id);
}
