package com.ssafy.trend_gaza.user.dto;


import lombok.Builder;

@Builder
public class RegisterRequest {

    private String userId;
    private String userName;
    private String password;
    private String mobile;
    private String emailId;
    private String emailDomain;
    private String gender;

    public RegisterRequest() {}

    public RegisterRequest(
            String userId,
            String userName,
            String password,
            String mobile,
            String emailId,
            String emailDomain,
            String gender) {
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
