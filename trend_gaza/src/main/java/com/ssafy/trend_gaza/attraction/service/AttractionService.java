package com.ssafy.trend_gaza.attraction.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;

public interface AttractionService {
	List<AttractionInfo> searchAttractions(Map<String, String> param);
}
