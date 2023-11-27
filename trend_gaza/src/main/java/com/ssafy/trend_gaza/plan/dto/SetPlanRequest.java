package com.ssafy.trend_gaza.plan.dto;

public class SetPlanRequest {

    private String title;
    private String startDate;
    private String endDate;
    private String userId;

    public SetPlanRequest() {}

    public SetPlanRequest(String title, String startDate, String endDate, String userId) {
        super();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
