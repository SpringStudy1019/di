package com.ssafy.trend_gaza.plan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trend_gaza.plan.dto.SelectAttractionPlanResponse;
import com.ssafy.trend_gaza.plan.dto.SetPlanRequest;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.dto.PlanResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" }, 
classes = {
		PlanServiceImpl.class })
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "com.ssafy" })
public class PlanServiceImplTest {
	
	@Autowired
	private PlanService planService;
	
	@Test
	@Transactional
	@DisplayName("여행 계획 세우기")
	void testRegisterPlan() {
		// given
		List<PlanRequest> planRequests = new ArrayList<PlanRequest>();
		planRequests.add(PlanRequest.builder()
				.attractionId(125266)
				.orderDate(1)
				.order(1)
				.build());
		planRequests.add(PlanRequest.builder()
				.attractionId(125267)
				.orderDate(1)
				.order(2)
				.build());
		
		// when
		int result = planService.registerPlan(planRequests, 1);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	@Transactional
	@DisplayName("여행 계획 수정하기")
	void testModifyPlan() {
		// given
		List<PlanRequest> planRequests = new ArrayList<PlanRequest>();
		planRequests.add(PlanRequest.builder()
				.attractionId(125266)
				.orderDate(1)
				.order(1)
				.build());
		planRequests.add(PlanRequest.builder()
				.attractionId(126481)
				.orderDate(1)
				.order(2)
				.build());
		
		// when
		int result = planService.modifyPlan(planRequests, 8);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	@Transactional
	@DisplayName("여행 계획 삭제 - 리더 탈퇴")
	void testDeletePlan1() {
		// given
		int planIdx = 8;
		String userId = "alswjd";
		
		// when
		int result = planService.deletePlan(1, userId);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	@Transactional
	@DisplayName("여행 계획 삭제 - 참가자 탈퇴")
	void testDeletePlan2() {
		// given
		int planIdx = 8;
		String userId = "ssafy";
		
		// when
		int result = planService.deletePlan(1, userId);
		
		// then
		assertEquals(1, result);
  }

	@DisplayName("내가 참여한 여행 계획 목록 조회하기")
	void testGetMyPlans() {
		// given
		String userId = "alswjd";
		
		// when
		List<PlanResponse> result = planService.getMyPlans(userId);
		
		// then
		assertEquals(1, result.size());
	}
	
	@Test
	@DisplayName("여행 계획 조회")
	void testGetAttractionPlan() {
		// given
		int attractionPlanId = 1;
		
		// when
		List<SelectAttractionPlanResponse> result = planService.getSelectAttractionPlan(attractionPlanId);
		
		// then
		assertEquals(2, result.size());
	}
	
	@Test
	@Transactional
	@DisplayName("여행 계획 세우기 방 등록")
	void testSetPlan() {
		// given
		SetPlanRequest setPlanRequest = new SetPlanRequest();
		setPlanRequest.setTitle("부산 여행 가자");
		setPlanRequest.setStartDate(LocalDate.now().toString());
		setPlanRequest.setEndDate(LocalDate.now().plusDays(1).toString());
		setPlanRequest.setUserId("alswjd");
		
		// when
		int result = planService.setPlan(setPlanRequest);
		
		// then
		assertEquals(1, result);
	}

}
