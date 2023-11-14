package com.ssafy.trend_gaza.attraction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.attraction.service.AttractionService;

@Controller()
@RequestMapping("/attractions")
public class AttractionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	private final AttractionService attractionService;
	
	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
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
	
}
