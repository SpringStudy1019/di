package com.ssafy.trend_gaza.plan.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.plan.entity.Plan;


@Mapper
public interface PlanMapper {
	int registerPlan(Map<String, Object> param);
	int modifyPlan(Map<String, Object> param);
	Plan IsPlanOwner(Map<String, Object> param);
	int deletePlan(Map<String, Object> param);
	int deleteMyPlan(Map<String, Object> param);
}
