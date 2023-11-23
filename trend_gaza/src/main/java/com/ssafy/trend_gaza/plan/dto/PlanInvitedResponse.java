package com.ssafy.trend_gaza.plan.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class PlanInvitedResponse {
	
	private Integer planIdx;
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String userId;
	private Integer attractionPlanId;
	
	public PlanInvitedResponse() {}

	public PlanInvitedResponse(Integer planIdx, String title, LocalDateTime startDate, LocalDateTime endDate,
			String userId, Integer attractionPlanId) {
		super();
		this.planIdx = planIdx;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.attractionPlanId = attractionPlanId;
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

	public Integer getAttractionPlanId() {
		return attractionPlanId;
	}
	
	

}
