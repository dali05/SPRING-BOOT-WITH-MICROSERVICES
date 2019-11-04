package com.rest.customerservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.customerservice.exception.ErrorResponse;
import com.rest.customerservice.exception.Response;
import com.rest.customerservice.exception.ServiceException;
import com.rest.customerservice.model.Product;
import com.rest.customerservice.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restTemplate")
//@CrossOrigin(origins = "http://localhost:9999")
@Api(value="Customer Service")
public class RestTemplateImplController {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@RequestMapping(value = {"/","/customer"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
//	public String handleRequet() {
//		return "Welcome to RestTemplate Example";
//	}
	
	/**
	 * String jsonStr = "[{\r\n" + "\"name\":\"New York\",\r\n" + "\"number\": \"732921\",\r\n"+ "\"center\": {\r\n" + "\"latitude\": 38.895111,\r\n"  + " \"longitude\": -77.036667\r\n" + "}\r\n" + "},\r\n" + " {\r\n"+ "\"name\": \"San Francisco\",\r\n" +\"number\":\"298732\",\r\n"+ "\"center\": {\r\n" + "    \"latitude\": 37.783333,\r\n"+ "\"longitude\": -122.416667\r\n" + "}\r\n" + "}\r\n" + "]";

ObjectMapper mapper = new ObjectMapper();
MyPojo[] jsonObj = mapper.readValue(jsonStr, MyPojo[].class);

for (MyPojo itr : jsonObj) {
    System.out.println("Val of name is: " + itr.getName());
    System.out.println("Val of number is: " + itr.getNumber());
    System.out.println("Val of latitude is: " + 
        itr.getCenter().getLatitude());
    System.out.println("Val of longitude is: " + 
        itr.getCenter().getLongitude() + "\n");
}
	 * */
	
	
	@ApiOperation(value="Get Customer Service",notes="Get Customer Service by a particular inputs",response=RestTemplateImplController.class)
	
	@RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	public String handleRequest() {
		return "Welcome to RestTemplate Example";
	}
	
	
	@ApiOperation(value="Create Product",notes="Creation of a new Product",response=RestTemplateImplController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product Created successfully"),
            @ApiResponse(code = 401, message = "You are Not authorized to create Product"),
            @ApiResponse(code = 403, message = "Create Product is forbidden"),
            @ApiResponse(code = 404, message = "Resource Not found")
    })
	
	@PostMapping(value = "/createProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<String> createProduct(@RequestBody final Product product) {
		
		//Use HttpHeaders for setting header values in request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
//		Use the HttpEntity to wrap the request object. Here, we wrap the Product object to send it to the request body.
		HttpEntity<Product> entity = new HttpEntity<>(product,headers);
		
		//after response entity there is one more parameter you can use that is parameters. you can pass parameters after that
		ResponseEntity<String> response = restTemplate.exchange(Constants.BASE_URL+"/product-service/product/createProduct", 
												HttpMethod.POST,
												entity,
												String.class);
		
		System.out.println("Response Product >> "+response.getBody()+" >> "+response.getStatusCode());
		if(response.getStatusCode() == HttpStatus.CREATED) {
			return new ResponseEntity<>(response.getBody(),response.getStatusCode());
		}
		
		return new ResponseEntity<>(response.getBody(),HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value= "/updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Product> updateProduct(@RequestBody final Product product, @RequestParam final String id) throws ServiceException{
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Product> response = restTemplate.exchange(Constants.BASE_URL+"/product-service/product/updateProduct/{id}",
				HttpMethod.PUT,
				entity,
				Product.class,
				id);
		
		Product productResponse = (Product) response.getBody();
		
		if(response.getStatusCode() == HttpStatus.OK) {
			return new ResponseEntity<>(productResponse,response.getStatusCode());
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping(value = "/removeProd")
//	public ResponseEntity<Void> removeProduct(@RequestParam(name ="id") String id){
	public ResponseEntity<String> removeProduct(@RequestParam String id){
//		restTemplate.delete(Constants.BASE_URL+"/product-service/product/removeProduct/{id}", id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(Constants.BASE_URL+"/product-service/product/removeProd/{id}", 
											HttpMethod.DELETE,
											entity,
											String.class,
											id);
		if(response.getStatusCode() != HttpStatus.OK) {
			return new ResponseEntity<>(response.getBody(),response.getStatusCode());
		}
		
		return new ResponseEntity<>(response.getBody(),HttpStatus.OK);
	}
	
	/**
	 * GET List of products using a Wrapper Class
	 * @return
	 */
//	@HystrixCommand(fallbackMethod = "defaultFallbackGetProducts")
	@RequestMapping(value = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private ResponseEntity<Object> getProducts() {
		Product[] products = restTemplate.getForObject(Constants.BASE_URL+"/product-service/product/getProducts", Product[].class);

		if(products != null) {
			System.out.println("productList: "+products);
			
			List<Product> productList = Arrays.asList(products);
			System.out.println("productList : "+productList);
			
			return new ResponseEntity<Object>(products,HttpStatus.OK);
		}
		
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			
//			HttpEntity<String> entity = new HttpEntity<>(headers);
//			
//			ResponseEntity<Product> response = restTemplate.exchange(Constants.BASE_URL+"/product-service/product/getProducts", 
//												HttpMethod.GET,
//												entity,
//												Product.class);
//			
//			if(response.getStatusCode() != HttpStatus.OK) {	
//				System.out.println("productList: "+response.getBody());
//				
//				List<Product> productList = Arrays.asList(response.getBody());
//				System.out.println("productList : "+productList);
//				
//				return new ResponseEntity<Object>(productList,HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new RuntimeException();
//		}
		
		return new ResponseEntity<>("Products Not Found",HttpStatus.NOT_FOUND);
	}
	
//	private ResponseEntity<Object> defaultFallbackGetProducts(){
//		System.out.println("Service Unavailable..! Server is currently unable to handle the request due to a temporary overloading or maintenance of the server");
//		return new ResponseEntity<>("Service Unavailable..! Server is currently unable to handle the request due to a temporary overloading or maintenance of the server",HttpStatus.SERVICE_UNAVAILABLE);
//	}
	
	/**
	 * GET Product Entity using a Wrapper Class
	 * @return
	 */
	@RequestMapping(value = "/getProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private ResponseEntity<Product> getProductById(@PathVariable final String id){
		Product product = restTemplate.getForObject(Constants.BASE_URL+"/product-service/product/getProductById/{id}", Product.class,id);
		System.out.println(">>> "+product);
		if(product != null) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * GET Product Entity using a Wrapper Class
	 * @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProdById/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private ResponseEntity<Object> getProdById(@PathVariable final String id) throws ServiceException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Object> response = restTemplate.exchange(Constants.BASE_URL+"/product-service/product/getProdById/{id}",
				HttpMethod.GET,
				entity,
				Object.class,
				id);
		
//		if(response.getStatusCode() != HttpStatus.OK) {
//			throw new ServiceException(response.getStatusCode().value(),"customer-service >> Product not found, please check the inputs");
//		}
		
		/*ObjectMapper mapper = new ObjectMapper();
		MyPojo[] jsonObj = mapper.readValue(jsonStr, MyPojo[].class);

		for (MyPojo itr : jsonObj) {
		    System.out.println("Val of name is: " + itr.getName());
		    System.out.println("Val of number is: " + itr.getNumber());
		    System.out.println("Val of latitude is: " + 
		        itr.getCenter().getLatitude());
		    System.out.println("Val of longitude is: " + 
		        itr.getCenter().getLongitude() + "\n");
		}*/
		
		if(response.getStatusCode() != HttpStatus.OK) {
			Object obj = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			
			/*List<ErrorResponse> objList = mapper.convertValue(
					obj, 
				    new TypeReference<List<ErrorResponse>>(){}
				);*/
			
			/* Working
			 * ErrorResponse errObj = mapper.convertValue(
					obj, 
				    new TypeReference<ErrorResponse>(){}
				);*/
			
			ErrorResponse errObj = mapper.convertValue(obj, ErrorResponse.class);
			
//			ErrorResponse err = (ErrorResponse) obj;
			System.out.println(">>>> "+errObj+" >> "+errObj);
			return new ResponseEntity<>(errObj,HttpStatus.NOT_FOUND);
		}else {
			Object obj = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			
			/*List<Response> objList = mapper.convertValue(
					obj, 
				    new TypeReference<List<Response>>(){}
				);*/
			
			/* Working
			 * Response respObj = mapper.convertValue(
					obj, 
				    new TypeReference<Response>(){}
				);*/
			
			Response respObj = mapper.convertValue(obj, Response.class);
			
//			Response res = (Response) obj;
			System.out.println(">>>> "+respObj+" >> "+respObj);
			return new ResponseEntity<>(respObj,HttpStatus.OK);
		}
	}
	
//	@ExceptionHandler(value = ServiceException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	public ResponseEntity<ErrorResponse> exceptionHandler(ServiceException ex){
//		System.out.println("customerservice >> Inside exceptionHandler");
//		ErrorResponse response = new ErrorResponse(ex.getErrorCode(),ex.getMessage());
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}
}
