package com.ssafy.trend_gaza.user.dto;

public class LoginRequest {

	private final String userId;
	private final String userPwd;
	
	
	public LoginRequest(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	
	public String getUserId() {
		return userId;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	
}
