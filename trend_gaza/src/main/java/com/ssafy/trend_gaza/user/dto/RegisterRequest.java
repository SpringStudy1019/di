package com.ssafy.trend_gaza.user.dto;

public class RegisterRequest {

	private final String userId;
	private final String userName;
	private final String password;
	private final String mobile;
	private final String emailId;
	private final String emailDomain;
	private final String gender;
	
	public RegisterRequest(String userId, String userName, String password, String mobile, String emailId,
			String emailDomain, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.mobile = mobile;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.gender = gender;
	}
	

	public String getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public String getEmailDomain() {
		return emailDomain;
	}
	
	public String getGender() {
		return gender;
	}
	

}
