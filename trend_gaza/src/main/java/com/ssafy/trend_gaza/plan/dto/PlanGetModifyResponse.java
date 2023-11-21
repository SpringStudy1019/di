package com.ssafy.trend_gaza.plan.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class PlanGetModifyResponse {
	
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public PlanGetModifyResponse() {}

	public PlanGetModifyResponse(String title, LocalDate startDate, LocalDate endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
}
