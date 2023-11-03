package com.ssafy.trend_gaza.user.dto;

public class FindIdRequest {

	private String userName;
	private String mobile;
	
	
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
		return "FindIdRequest [userName=" + userName + ", mobile=" + mobile + "]";
	}

}
