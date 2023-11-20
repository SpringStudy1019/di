package com.ssafy.trend_gaza.plan.controller;

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

import com.ssafy.trend_gaza.plan.dto.AcceptInvitationRequest;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;
import com.ssafy.trend_gaza.plan.entity.Plan;
import com.ssafy.trend_gaza.plan.service.PlanService;
import com.ssafy.trend_gaza.util.AuthenticationUtil;

@Controller
@RequestMapping("/plans")
public class PlanController {
	
	private final PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@PostMapping("/{planIdx}")
	public ResponseEntity<?> registerPlan(@PathVariable int planIdx,
			@RequestBody List<PlanRequest> planRequest) {
		planService.registerPlan(planRequest, planIdx);
		return ResponseEntity.created(URI.create("")).build();		// URI 경로 지정 필요함
	}
	
	@PutMapping("/{attractionPlanId}")
	public ResponseEntity<?> modifyPlan(@PathVariable int attractionPlanId,
			@RequestBody List<PlanRequest> planRequest) {
		planService.modifyPlan(planRequest, attractionPlanId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{planIdx}")
	public ResponseEntity<?> deletePlan(@PathVariable int planIdx) {
		String userId = AuthenticationUtil.getCurrentUserSocialId();
		int result = planService.deletePlan(planIdx, userId);
		if(result == 0) {
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
	public ResponseEntity<?> joinPlan(@RequestBody AcceptInvitationRequest acceptInvitationRequest) {
		// creatorId와 title을 가지고, unique 여행 계획을 찾는다
		System.out.println(acceptInvitationRequest.getCreatorId());
		System.out.println(acceptInvitationRequest.getJoinUserId());
		System.out.println(acceptInvitationRequest.getTitle());
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
		List<Plan> list = planService.getInvitedPlans(userId);
		return new ResponseEntity<List<Plan>>(list, HttpStatus.OK);		
	}
	
	/*
	 * 내가 만든 여행 가져오기
	 */
	@GetMapping("/created")
	public ResponseEntity<?> getCreatedPlans() {
		final String userId = AuthenticationUtil.getCurrentUserSocialId();
		return ResponseEntity.ok().body(planService.getCreatedPlans(userId));
	}
}
