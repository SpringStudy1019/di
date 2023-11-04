package com.ssafy.trend_gaza.attraction.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.service.AttractionService;

@RestController
@RequestMapping("/admin/attractions")
public class AttractionAdminController {
	
	private final AttractionService attractionService;
	
	public AttractionAdminController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}

	
	@PostMapping
	public ResponseEntity<Void> createAttraction(@RequestBody AttractionAdminRequest attractionAdminRequest) {
		attractionService.registerAdminAttraction(attractionAdminRequest);
		return ResponseEntity.created(URI.create("/admin/attractions")).build();		
	}
}
