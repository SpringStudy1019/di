package com.ssafy.trend_gaza.plan.service;

import java.util.List;

import com.ssafy.trend_gaza.plan.dto.PlanRequest;

public interface PlanService {
	int registerPlan(List<PlanRequest> planRequest, int planIdx);
	int modifyPlan(List<PlanRequest> planRequest, int attractionPlanId);
}
