package com.ssafy.trend_gaza.plan.dto;


import lombok.Builder;

@Builder
public class PlanGetModifyResponse {
	
	private String title;
	private String startDate;
	private String endDate;
	
	public PlanGetModifyResponse() {}

	public PlanGetModifyResponse(String title, String startDate, String endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
}
