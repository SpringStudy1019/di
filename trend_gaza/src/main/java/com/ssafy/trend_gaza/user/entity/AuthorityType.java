package com.ssafy.trend_gaza.user.entity;

public enum AuthorityType {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    private AuthorityType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
