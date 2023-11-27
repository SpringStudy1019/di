package com.ssafy.trend_gaza.user.dto;

public class FindPwdRequest {

    private String userId;
    private String userName;
    private String mobile;

    public FindPwdRequest() {}

    public FindPwdRequest(String userId, String userName, String mobile) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.mobile = mobile;
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
}
