package com.rest.customerservice.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
}
