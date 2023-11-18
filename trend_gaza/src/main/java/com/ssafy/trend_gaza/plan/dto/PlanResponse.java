package com.ssafy.trend_gaza.plan.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class PlanResponse {

	private Integer planIdx;
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String userId;
	
	public PlanResponse() {}

	public PlanResponse(Integer planIdx, String title, LocalDateTime startDate, LocalDateTime endDate, String userId) {
		super();
		this.planIdx = planIdx;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
	}

	public Integer getPlanIdx() {
		return planIdx;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public String getUserId() {
		return userId;
	}
	
}
