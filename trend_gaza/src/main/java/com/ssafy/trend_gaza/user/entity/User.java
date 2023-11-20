package com.ssafy.trend_gaza.user.entity;

import lombok.Builder;

@Builder
public class User {

	private String userId;
	private String userName;
	private String password;
	private String mobile;
	private String emailId;
	private String emailDomain;
	private String gender;
	private AuthorityType authority;
	private String joinDate;
	private String socialId;
	private PlatformType platform;
	private String refreshToken;
	private String imgUrl;
	
	public User() {}
	
	public User(String userId, String userName, String password, String mobile, String emailId, String emailDomain,
			String gender, AuthorityType authority, String joinDate, String socialId, PlatformType platform,
			String refreshToken, String imgUrl) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.mobile = mobile;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.gender = gender;
		this.authority = authority;
		this.joinDate = joinDate;
		this.socialId = socialId;
		this.platform = platform;
		this.refreshToken = refreshToken;
		this.imgUrl = imgUrl;
	}

	
	public PlatformType getPlatform() {
		return platform;
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

	public AuthorityType getAuthority() {
		return authority;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public String getSocialId() {
		return socialId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
}
