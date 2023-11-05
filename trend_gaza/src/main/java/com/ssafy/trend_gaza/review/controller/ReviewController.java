package com.ssafy.trend_gaza.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
@Slf4j
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody ReviewRegisterRequest reviewRegisterRequest) {
		log.debug("register Dto : {}", reviewRegisterRequest);
		try {
			reviewService.register(reviewRegisterRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
