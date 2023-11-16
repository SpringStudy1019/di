package com.ssafy.trend_gaza.follow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.follow.service.FollowService;
import com.ssafy.trend_gaza.like.dto.LikeRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/follow")
@CrossOrigin("*")
@Slf4j
public class FollowController {

	private FollowService followService;

	public FollowController(FollowService followService) {
		super();
		this.followService = followService;
	}
	
	@PostMapping
	public ResponseEntity<?> onFollow(@RequestBody FollowRequest followRequest) {
		try {
			int result = followService.onFollow(followRequest);
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
	public ResponseEntity<?> offFollow(@RequestBody FollowRequest followRequest) {
		try {
			int result = followService.offFollow(followRequest);
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.CREATED);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value="/{followeeId}")
	public ResponseEntity<?> selectUsers(@PathVariable String followeeId) {
		try {
			List<String> result = followService.listFollowers(followeeId);
			if (result != null && !result.isEmpty()) {
				return new ResponseEntity<List<String>>(result, HttpStatus.OK);				
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);				
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
