package com.ssafy.trend_gaza.attraction.controller;

import java.net.URI;

import javax.servlet.ServletContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.service.AttractionService;

@RestController
@RequestMapping("/admin/attractions")
public class AttractionAdminController {
	
	private final String UPLOAD_PATH = "/upload";
	
	private final AttractionService attractionService;
	private final ServletContext servletContext;
	
	public AttractionAdminController(AttractionService attractionService, ServletContext servletContext) {
		this.attractionService = attractionService;
		this.servletContext = servletContext;
	}

	
	@PostMapping
	public ResponseEntity<Void> createAttraction(@RequestBody AttractionAdminRequest attractionAdminRequest) {
		attractionService.registerAdminAttraction(attractionAdminRequest);
		return ResponseEntity.created(URI.create("/admin/attractions")).build();		
	}
	
	@PostMapping("/upload") public ResponseEntity<?> uploadAttractionImage(@RequestPart("image") MultipartFile multipartFile) throws Exception {
		if(multipartFile.isEmpty() || multipartFile.getOriginalFilename().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		String imageUrl = attractionService.uploadAttractionImage(multipartFile, servletContext.getRealPath(UPLOAD_PATH)); 
		return ResponseEntity.ok().body(imageUrl); 
	}
	 
	
}
