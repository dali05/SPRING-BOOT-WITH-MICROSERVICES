package com.rest.customerservice.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int respCode;
	private String respMsg;
	private T responseObj;
	
	public Response() {
	}

	
	public Response(int respCode, String respMsg, T responseObj) {
		super();
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.responseObj = responseObj;
	}


	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public T getResponseObj() {
		return responseObj;
	}

	public void setResponseObj(T responseObj) {
		this.responseObj = responseObj;
	}

	@Override
	public String toString() {
		return "Response [respCode=" + respCode + ", respMsg=" + respMsg + ", responseObj=" + responseObj + "]";
	}
}
