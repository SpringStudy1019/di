package com.ssafy.trend_gaza.plan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.repository.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService {

	private final PlanMapper planMapper;
	
	public PlanServiceImpl(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}
	
	@Override
	public int registerPlan(List<PlanRequest> planRequest, int planIdx) {
		int result = 0;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(planRequest);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("attraction", json);
			param.put("planIdx", planIdx);
			result = planMapper.registerPlan(param);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int modifyPlan(List<PlanRequest> planRequest, int attractionPlanId) {
		int result = 0;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(planRequest);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("attraction", json);
			param.put("attractionPlanId", attractionPlanId);
			result = planMapper.modifyPlan(param);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
