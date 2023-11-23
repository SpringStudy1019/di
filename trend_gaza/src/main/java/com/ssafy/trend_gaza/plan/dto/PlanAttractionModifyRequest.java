package com.ssafy.trend_gaza.plan.dto;

import java.util.List;

import lombok.Builder;

@Builder
public class PlanAttractionModifyRequest {
	
	private List<PlanRequest> list;
	private String startDate;
	private String endDate;
	
	public PlanAttractionModifyRequest() {}

	public PlanAttractionModifyRequest(List<PlanRequest> list, String startDate, String endDate) {
		super();
		this.list = list;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<PlanRequest> getList() {
		return list;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	
}
