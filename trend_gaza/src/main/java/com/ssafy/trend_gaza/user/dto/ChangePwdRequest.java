package com.ssafy.trend_gaza.user.dto;

public class ChangePwdRequest {

	private  String userId;
	private  String password;
	private  String newPassword;
		
	public ChangePwdRequest() {}

	public ChangePwdRequest(String userId, String password, String newPassword) {
		super();
		this.userId = userId;
		this.password = password;
		this.newPassword = newPassword;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
