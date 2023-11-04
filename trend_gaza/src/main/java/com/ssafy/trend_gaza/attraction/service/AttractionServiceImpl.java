package com.ssafy.trend_gaza.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.entity.AttractionDetail;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.attraction.repository.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

	private final AttractionMapper attractionMapper;
	
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}


	@Override
	public List<AttractionInfo> searchAttractions(Map<String, String> param) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sido = param.get("sido");
		String contentTypeId = param.get("contentTypeId");
		String keyword = param.get("keyword");
		
		map.put("sido", sido == null ? "" : sido);
		map.put("contentTypeId", contentTypeId == null ? "" : contentTypeId);
		map.put("keyword", keyword == null ? "" : keyword);
		
		return attractionMapper.searchAttractions(map);
		//return result.stream().map(AttractionResponse::of).toList();
	}


	@Override
	//@Transactional
	public void registerAdminAttraction(AttractionAdminRequest attractionAdminRequest) {
		
		attractionMapper.registerAdminAttraction(attractionAdminRequest);
		//AttractionDetail attractionDetail = new AttractionDetail();
		//attractionDetail.setContentId(contentId);
//		attractionMapper.registerAttractionDetail(attractionAdminRequest);
	}

}
