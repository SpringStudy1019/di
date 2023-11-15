package com.ssafy.trend_gaza.attraction.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;

@Mapper
public interface AttractionMapper {
	List<AttractionInfo> searchAttractions(Map<String, Object> param);
	void registerAdminAttraction(AttractionAdminRequest attractionAdminRequest);
	void registerAttractionDetail(AttractionAdminRequest attractionAdminRequest);
	AttractionInfo getAttractionDetail(int id);
	List<String> attractionNameList();
}
