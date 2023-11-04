package com.ssafy.trend_gaza.user.dto;

public class FindIdRequest {

	private final String userName;
	private final String mobile;
	
	public FindIdRequest(String userName, String mobile) {
		super();
		this.userName = userName;
		this.mobile = mobile;
	}

	
	public String getUserName() {
		return userName;
	}
	
	public String getMobile() {
		return mobile;
	}

}
