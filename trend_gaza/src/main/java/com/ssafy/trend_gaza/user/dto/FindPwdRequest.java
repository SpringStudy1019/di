package com.ssafy.trend_gaza.user.dto;

public class FindPwdRequest {

	private String userId;
	private String userName;
	private String mobile;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	@Override
	public String toString() {
		return "FindPwdRequest [userId=" + userId + ", userName=" + userName + ", mobile=" + mobile + "]";
	}

}
