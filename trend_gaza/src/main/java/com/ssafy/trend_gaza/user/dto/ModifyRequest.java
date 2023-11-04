package com.ssafy.trend_gaza.user.dto;

public class ModifyRequest {

//	private  String userId;
//	private  String userName;
//	private  String mobile;
//	private  String emailId;
//	private  String emailDomain;
//	private  String gender;

	private final String userId;
	private final String userName;
	private final String mobile;
	private final String emailId;
	private final String emailDomain;
	private final String gender;
	
//	public ModifyRequest() {}
	
	public ModifyRequest(String userId, String userName, String mobile, String emailId, String emailDomain,
			String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
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
