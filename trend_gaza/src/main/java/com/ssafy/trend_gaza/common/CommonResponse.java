package com.ssafy.trend_gaza.common;

public class CommonResponse {
	
	protected Boolean isSuccess;
	
	protected int code;
	
	protected String message;

	
	public CommonResponse() {
		super();
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean iscuccess) {
		this.isSuccess = iscuccess;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}
