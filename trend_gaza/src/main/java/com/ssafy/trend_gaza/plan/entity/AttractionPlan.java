package com.ssafy.trend_gaza.plan.entity;

import lombok.Builder;

@Builder
public class AttractionPlan {
	
	private Integer attractionPlanId;
	private Integer planIdx;
	private String contentId;
	
	
	public AttractionPlan() {}

	public AttractionPlan(Integer attractionPlanId, Integer planIdx, String contentId) {
		super();
		this.attractionPlanId = attractionPlanId;
		this.planIdx = planIdx;
		this.contentId = contentId;
	}

	
	public Integer getAttractionPlanId() {
		return attractionPlanId;
	}

	public Integer getPlanIdx() {
		return planIdx;
	}

	public String getContentId() {
		return contentId;
	}
	
}
