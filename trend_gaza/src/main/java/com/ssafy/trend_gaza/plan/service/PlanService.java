package com.ssafy.trend_gaza.plan.service;


import com.ssafy.trend_gaza.plan.dto.AcceptInvitationRequest;
import com.ssafy.trend_gaza.plan.dto.PlanDetailResponse;
import com.ssafy.trend_gaza.plan.dto.PlanGetModifyResponse;
import com.ssafy.trend_gaza.plan.dto.PlanInvitedResponse;
import com.ssafy.trend_gaza.plan.dto.PlanModifyRequest;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.PlanResponse;
import com.ssafy.trend_gaza.plan.dto.SelectAttractionPlanResponse;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;
import com.ssafy.trend_gaza.user.entity.User;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

public interface PlanService {
    int registerPlan(List<PlanRequest> planRequest, int planIdx);

    int modifySelectPlan(List<PlanRequest> planRequest, int attractionPlanId);

    int deletePlan(int planIdx, String userId);

    List<PlanResponse> getMyPlans(String userId);

    /* 알림을 받은 사용자가 여행 계획을 수락하면, 그 여행에 참여하게 된다 */
    int joinPlan(int planIdx, String userId);

    /* 여행 계획표 (방) 만들기 */
    int setPlan(@RequestBody SetPlanRequest setPlanRequest);

    /* 초대된 여행 전체 조회하기 */
    List<PlanInvitedResponse> getInvitedPlans(String userId);

    /* 초대 수락할 여행 1개의 plan_idx 조회하기 */
    int getInvitedPlan(AcceptInvitationRequest acceptInvitationRequest);

    /* 내가 만든 여행 조회하기 */
    List<PlanResponse> getCreatedPlans(String userId);

    /* 여행 일정 상세보기 */
    PlanDetailResponse getPlanDetail(int planIdx);

    /* 유저가 세운 여행 계획 조회 */
    List<SelectAttractionPlanResponse> getSelectAttractionPlan(int attractionPlanId);

    PlanGetModifyResponse getModifyPlan(int planIdx);

    int modifyPlan(int planIdx, PlanModifyRequest planModifyRequest);

    List<User> getParticipant(int planIdx);
}
