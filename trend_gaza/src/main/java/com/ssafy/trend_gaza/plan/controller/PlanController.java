package com.ssafy.trend_gaza.plan.controller;


import com.ssafy.trend_gaza.plan.dto.AcceptInvitationRequest;
import com.ssafy.trend_gaza.plan.dto.PlanInvitedResponse;
import com.ssafy.trend_gaza.plan.dto.PlanModifyRequest;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;
import com.ssafy.trend_gaza.plan.service.PlanService;
import com.ssafy.trend_gaza.util.AuthenticationUtil;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/attraction/{planIdx}")
    public ResponseEntity<?> registerPlan(
            @PathVariable int planIdx, @RequestBody List<PlanRequest> planRequest) {
        planService.registerPlan(planRequest, planIdx);
        return ResponseEntity.created(URI.create("")).build(); // URI 경로 지정 필요함
    }

    /*
     * 선택했던 여행지 수정
     */
    @PutMapping("/{attractionPlanId}")
    public ResponseEntity<?> modifySelectPlan(
            @PathVariable int attractionPlanId, @RequestBody List<PlanRequest> planRequest) {
        planService.modifySelectPlan(planRequest, attractionPlanId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{planIdx}")
    public ResponseEntity<?> deletePlan(@PathVariable int planIdx) {
        String userId = AuthenticationUtil.getCurrentUserSocialId();
        int result = planService.deletePlan(planIdx, userId);
        if (result == 0) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getMyPlans() {
        final String userId = AuthenticationUtil.getCurrentUserSocialId();
        return ResponseEntity.ok().body(planService.getMyPlans(userId));
    }

    /*
     * 초대된 여행 계획에 참여하기
     */
    @PostMapping("/join")
    public ResponseEntity<?> joinPlan(
            @RequestBody AcceptInvitationRequest acceptInvitationRequest) {
        // creatorId와 title을 가지고, unique 여행 계획을 찾는다
        //		System.out.println(acceptInvitationRequest.getCreatorId());
        //		System.out.println(acceptInvitationRequest.getJoinUserId());
        //		System.out.println(acceptInvitationRequest.getTitle());
        int planIdx = planService.getInvitedPlan(acceptInvitationRequest);
        String joinUserId = acceptInvitationRequest.getJoinUserId();
        planService.joinPlan(planIdx, joinUserId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
     * 여행 계획표 (방) 만들기
     */
    @PostMapping
    public ResponseEntity<?> setPlan(@RequestBody SetPlanRequest setPlanRequest) {
        //		setPlanRequest.setUserId(AuthenticationUtil.getCurrentUserSocialId());
        planService.setPlan(setPlanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
     * 내가 초대된 여행 가져오기
     */
    @GetMapping("/invite/{userId}")
    public ResponseEntity<?> getInvitedPlans(@PathVariable String userId) {
        // userId를 받으면 user_plan 테이블에서 plan_idx를 받아오기.
        // plan_idx로 plan 관련된 정보 뽑아오기
        return new ResponseEntity<List<PlanInvitedResponse>>(
                planService.getInvitedPlans(userId), HttpStatus.OK);
    }

    /*
     * 내가 만든 여행 가져오기
     */
    @GetMapping("/created/{userId}")
    public ResponseEntity<?> getCreatedPlans(@PathVariable String userId) {
        return ResponseEntity.ok().body(planService.getCreatedPlans(userId));
    }

    /*
     * creator가 여행 상세정보 가져오기
     * 여행에 참여하는 모든 사람들이 자세한 여행 일정을 볼 수 있으며
     * 여행 테이블의 PK, 여행 계획 제목, 방문 일자와 방문 순서가 정해진 여행지, 여행 시작과 끝 일자, 모든 참여자 정보를 반환 받는다.
     * return PlanDetailResponse
     */
    @GetMapping("/detail")
    public ResponseEntity<?> getPlanDetail(
            @RequestBody AcceptInvitationRequest acceptInvitationRequest) {
        int planIdx = planService.getInvitedPlan(acceptInvitationRequest);
        return ResponseEntity.ok().body(planService.getPlanDetail(planIdx));
    }

    /*
     * 저장된 여행 계획 경로를 가져오기
     */
    @GetMapping("/{attractionPlanId}")
    public ResponseEntity<?> getSelectAttractionPlan(@PathVariable int attractionPlanId) {
        return ResponseEntity.ok().body(planService.getSelectAttractionPlan(attractionPlanId));
    }

    /*
     * 수정 페이지 로딩에 필요한 계획 조회
     */
    @GetMapping("/modify/{planIdx}")
    public ResponseEntity<?> getModifyPlan(@PathVariable int planIdx) {
        return ResponseEntity.ok().body(planService.getModifyPlan(planIdx));
    }

    @PutMapping("/modify/{planIdx}")
    public ResponseEntity<?> modifyPlan(
            @PathVariable int planIdx, @RequestBody PlanModifyRequest planModifyRequest) {
        return ResponseEntity.ok().body(planService.modifyPlan(planIdx, planModifyRequest));
    }

    /*
     * 여행 참여자 조회
     */
    @GetMapping("/people/{planIdx}")
    public ResponseEntity<?> getParticipant(@PathVariable int planIdx) {
        return ResponseEntity.ok().body(planService.getParticipant(planIdx));
    }
}
