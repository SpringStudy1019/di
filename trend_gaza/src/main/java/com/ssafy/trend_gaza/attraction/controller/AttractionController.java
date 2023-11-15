package com.ssafy.trend_gaza.attraction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trend_gaza.attraction.dto.AttractionAutoSearchResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.attraction.service.AttractionService;
import com.ssafy.trend_gaza.common.CommonResponse;
import com.ssafy.trend_gaza.common.ResponseService;
import com.ssafy.trend_gaza.util.TrieAlgorithmUtil.Node;

@Controller()
@RequestMapping("/attractions")
@CrossOrigin("*")
public class AttractionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	private final AttractionService attractionService;
	private final ResponseService responseService;
	
	public AttractionController(AttractionService attractionService, ResponseService responseService) {
		this.attractionService = attractionService;
		this.responseService = responseService;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AttractionDetailResponse> findAttraction(@PathVariable int id) {
		return ResponseEntity.ok(attractionService.findAttraction(id));
	}
	
	@GetMapping
	public ResponseEntity<List<AttractionResponse>> findAttractions(@RequestParam Map<String, String> map) {
		return ResponseEntity.ok(new ArrayList<AttractionResponse>());
	}
	
	@GetMapping("/trend")
	public ResponseEntity<List<AttractionResponse>> findTrendAttractions() {
		return ResponseEntity.ok(new ArrayList<AttractionResponse>());
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<AttractionInfo>> searchAttractions(@RequestParam Map<String, String> map) {
		logger.debug("searchAttractions call!");
		return ResponseEntity.ok(attractionService.searchAttractions(map));	
	}
	
	@GetMapping("/auto-search")
	public ResponseEntity<CommonResponse> AutoSearchAttractions(@RequestParam String keyword) {
		Node node = attractionService.search(keyword);
		if(node == null) {
			return ResponseEntity.ok().build();
		}
		List<AttractionAutoSearchResponse> result = attractionService.autoComplete(keyword, node);
		
		return ResponseEntity.ok(responseService.getDataResponse(result));
	}
	
}
