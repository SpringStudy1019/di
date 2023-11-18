package com.ssafy.trend_gaza.plan.entity;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class Plan {
	
	private Integer planIdx;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String userId;
	
	
	public Plan() {}

	public Plan(Integer planIdx, LocalDateTime startDate, LocalDateTime endDate, String userId) {
		super();
		this.planIdx = planIdx;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
	}

	
	public Integer getPlanIdx() {
		return planIdx;
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
