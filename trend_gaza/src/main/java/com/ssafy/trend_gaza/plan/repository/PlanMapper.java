package com.ssafy.trend_gaza.plan.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PlanMapper {
	int registerPlan(Map<String, Object> param);
	int modifyPlan(Map<String, Object> param);
}
