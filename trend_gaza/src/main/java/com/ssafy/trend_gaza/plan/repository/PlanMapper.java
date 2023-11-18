package com.ssafy.trend_gaza.plan.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.plan.dto.PlanResponse;


@Mapper
public interface PlanMapper {
	int registerPlan(Map<String, Object> param);
	int modifyPlan(Map<String, Object> param);
	List<PlanResponse> getMyPlans(String userId);
}
