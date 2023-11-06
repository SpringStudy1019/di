package com.ssafy.trend_gaza.like.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.like.dto.LikeRequest;
import com.ssafy.trend_gaza.like.service.LikeService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/like")
@CrossOrigin("*")
@Slf4j
public class LikeController {

	private LikeService likeService;

	public LikeController(LikeService likeService) {
		super();
		this.likeService = likeService;
	}
	
	
	@PostMapping
	public ResponseEntity<?> onLike(@RequestBody LikeRequest likeRequest) {
		try {
			int result = likeService.onLike(likeRequest);
			log.debug(String.valueOf(result));
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.CREATED);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> offLike(@RequestBody LikeRequest likeRequest) {
		try {
			int result = likeService.offLike(likeRequest);
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.CREATED);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
