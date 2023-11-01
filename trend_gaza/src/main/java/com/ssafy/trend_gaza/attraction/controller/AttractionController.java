package com.ssafy.trend_gaza.attraction.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionResponse;

@Controller()
@RequestMapping("/attractions")
public class AttractionController {
	
	@GetMapping("/{id}")
	public ResponseEntity<AttractionDetailResponse> findAttraction() {
		return ResponseEntity.ok(new AttractionDetailResponse());
	}
	
	@GetMapping
	public ResponseEntity<List<AttractionResponse>> findAttractions(@RequestParam Map<String, String> map) {
		return ResponseEntity.ok(new ArrayList<AttractionResponse>());
	}
	
	@GetMapping("/trend")
	public ResponseEntity<List<AttractionResponse>> findTrendAttractions() {
		return ResponseEntity.ok(new ArrayList<AttractionResponse>());
	}
	
	
}
