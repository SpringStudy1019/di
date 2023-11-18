package com.ssafy.trend_gaza.plan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.trend_gaza.plan.dto.PlanRequest;
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

}
