package com.ssafy.trend_gaza.plan.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.trend_gaza.plan.dto.AcceptInvitationRequest;
import com.ssafy.trend_gaza.plan.dto.PlanDetailResponse;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.PlanResponse;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;
import com.ssafy.trend_gaza.plan.entity.Plan;

public interface PlanService {
	int registerPlan(List<PlanRequest> planRequest, int planIdx);
	int modifyPlan(List<PlanRequest> planRequest, int attractionPlanId);
	int deletePlan(int planIdx, String userId);
	List<PlanResponse> getMyPlans(String userId);
	
	/* 알림을 받은 사용자가 여행 계획을 수락하면, 그 여행에 참여하게 된다 */
	int joinPlan(int planIdx, String userId);
	
	/* 여행 계획표 (방) 만들기 */
	int setPlan(@RequestBody SetPlanRequest setPlanRequest);
	
	/* 초대된 여행 전체 조회하기 */
	List<Plan> getInvitedPlans(String userId);
	
	/* 초대 수락할 여행 1개의 plan_idx 조회하기 */
	int getInvitedPlan(AcceptInvitationRequest acceptInvitationRequest);
	
	/* 내가 만든 여행 조회하기 */
	List<PlanResponse> getCreatedPlans(String userId);
	
	/* 여행 일정 상세보기 */
	PlanDetailResponse getPlanDetail(int planIdx);
}
