package com.ssafy.trend_gaza.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.PlanResponse;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;

public interface PlanService {
	int registerPlan(List<PlanRequest> planRequest, int planIdx);
	int modifyPlan(List<PlanRequest> planRequest, int attractionPlanId);
	int deletePlan(int planIdx, String userId);
	List<PlanResponse> getMyPlans(String userId);
	
	/* 알림을 받은 사용자가 여행 계획을 수락하면, 그 여행에 참여하게 된다 */
	int joinPlan(int planIdx, String userId);
	
	/* 여행 계획표 (방) 만들기 */
	int setPlan(@RequestBody SetPlanRequest setPlanRequest);
}
