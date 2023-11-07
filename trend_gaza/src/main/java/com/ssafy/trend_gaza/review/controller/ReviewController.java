package com.ssafy.trend_gaza.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.review.dto.ReviewModifyRequest;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.entity.Review;
import com.ssafy.trend_gaza.review.service.ReviewService;
import com.ssafy.trend_gaza.util.PageNavigation;

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
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody ReviewRegisterRequest reviewRegisterRequest) {
		try {
			reviewService.register(reviewRegisterRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) {
		try {
			List<Review> list = reviewService.list(map);
			PageNavigation pageNavigation = reviewService.makePageNavigation(map);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> modify(@RequestBody ReviewModifyRequest reviewModifyRequest, HttpSession session) {
		try {
			reviewService.modify(reviewModifyRequest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(String reviewIdx) {
		try {
			reviewService.delete(reviewIdx);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
