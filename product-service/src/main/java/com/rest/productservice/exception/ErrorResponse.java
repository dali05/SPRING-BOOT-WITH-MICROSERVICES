package com.rest.productservice.exception;

//@JsonIgnoreProperties(ignoreUnknown = true)
//public class ErrorResponse implements Serializable{
public class ErrorResponse {
//	private static final long serialVersionUID = 1L;
	
	private int errCode;
	private String errMsg;
	
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "ErrorResponse [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}
}
