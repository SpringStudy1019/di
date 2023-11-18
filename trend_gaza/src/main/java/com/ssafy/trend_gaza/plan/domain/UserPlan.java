package com.ssafy.trend_gaza.plan;


import lombok.Builder;

@Builder
public class UserPlan {
	
	private Integer userPlanId;
	private String userId;
	private Integer planIdx;
	
	
	public UserPlan() {}
	
	public UserPlan(Integer userPlanId, String userId, Integer planIdx) {
		super();
		this.userPlanId = userPlanId;
		this.userId = userId;
		this.planIdx = planIdx;
	}
	

	public Integer getUserPlanId() {
		return userPlanId;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getPlanIdx() {
		return planIdx;
	}
	
}
