package com.ssafy.trend_gaza.plan.dto;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class PlanModifyRequest {

	private String title;
	private LocalDate endDate;
	private LocalDate startDate;
	
	public PlanModifyRequest() {}

	public PlanModifyRequest(String title, LocalDate endDate, LocalDate startDate) {
		super();
		this.title = title;
		this.endDate = endDate;
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	
}
