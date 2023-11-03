package com.ssafy.trend_gaza.user.dto;

public class ChangePwdRequest {

	private String userId;
	private String password;
	private String newPassword;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	@Override
	public String toString() {
		return "ChangePwdRequest [userId=" + userId + ", password=" + password + ", newPassword=" + newPassword + "]";
	}

}
