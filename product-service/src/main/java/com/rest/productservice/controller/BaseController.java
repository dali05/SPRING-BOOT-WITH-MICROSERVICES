package com.rest.productservice.controller;

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

import com.rest.productservice.exception.Response;
import com.rest.productservice.exception.ServiceException;
import com.rest.productservice.model.Product;
import com.rest.productservice.service.ProductService;


/**
 * @GetMapping is an alias for @RequestMapping(method = RequestMethod.GET).
 * Like that value is an alias to path in @RequestMapping
 * 
 * @author sarang_korde
 *
 */

@RestController
@RequestMapping("/product")
public class BaseController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = {"/","/product"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String handleRequet() {
		return "Welcome to product-service provider";
	}
	
	/***
	 * @param product
	 * @return ResponseEntity<Object> with HttpStatus
	 */
//	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/createProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//	@RequestMapping(path = "/products", method = RequestMethod.POST)
	private ResponseEntity<String> createProduct(@RequestBody Product product){
		
		String msg = productService.createProduct(product);
		if(msg != null &&  msg.equals("Success")) {
			return new ResponseEntity<>("Successfully Created",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Getting Error While Creating Product",HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @param id
	 * @return ResponseEntity<Product> with HttpStatus
	 */
	@RequestMapping(value = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
	private ResponseEntity<Product> getProductById(@PathVariable String id){
		Product product = productService.getProduct(id);
		if(product != null) {
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
//--------------------------------------------------------
	/**
	 *  With exception handling example
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getProdById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private ResponseEntity<?> getProdById(@PathVariable String id) throws ServiceException{
		Product product = productService.getProduct(id);
		
		if(product == null) {
			throw new ServiceException("product-service >> Product not found, please check the inputs");
		}
		
		System.out.println(">>> "+product);
		
		Response response = new Response(HttpStatus.OK.value(),"Fetching record from DB",product);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
//	@ExceptionHandler({ServiceException.class})
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public ResponseEntity<ErrorResponse> exceptionHandler(ServiceException ex){
//		System.out.println("productservice >> Inside exceptionHandler");
//		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	-----------------------------------------------------------------------
	
	
	/**
	 * @return ResponseEntity<List<Product>> with HttpStatus
	 */
	@RequestMapping(value = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	private ResponseEntity<Object> getProducts(){
		List<Product> productList = productService.getProductList();
		if(productList != null && productList.size() > 0) {
			return new ResponseEntity<>(productList,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * @param product
	 * @param id
	 * @return ResponseEntity<Product> with HttpStatus
	 * @throws Response 
	 */
	@RequestMapping(value = "/updateProduct/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
//	@RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT)
	private ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable String id){
		Product updatedProduct = productService.updateProduct(product, id);
		if(updatedProduct != null) {
			return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	/**
	 * 
	 * @param id
	 * @return HttpStatus only
	 */
	@RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Void> removeProduct(@PathVariable String id){
		String msg = productService.removeProduct(id);
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
	@RequestMapping(value = "/removeProd/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> removeProd(@PathVariable String id){
		String msg = productService.removeProduct(id);
		if(msg != null && msg.equals("Success")) {
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>("Product not found for given id",HttpStatus.NOT_FOUND);
	}
}
