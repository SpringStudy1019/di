package com.ssafy.trend_gaza.attraction.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.entity.AttractionDetail;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;

public interface AttractionService {
	List<AttractionInfo> searchAttractions(Map<String, String> param);
	void registerAdminAttraction(AttractionAdminRequest attractionAdminRequest);
	String uploadAttractionImage(MultipartFile multipartFile, String realPath) throws Exception;
}
