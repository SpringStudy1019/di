package com.ssafy.trend_gaza.comment.controller;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.comment.dto.CommentRegisterRequest;
import com.ssafy.trend_gaza.comment.service.CommentService;

@RestController
@RequestMapping("/controller")
@CrossOrigin("*")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody CommentRegisterRequest commentRegisterRequest) {
		try {
			commentService.register(commentRegisterRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
//	@GetMapping(value = "/view/{reviewIdx}")
//	public ResponseEntity<?> list(@PathVariable int reviewIdx) {
//		try {
//			List<Comment> list = commentService.list(reviewIdx);
//			HttpHeaders header = new HttpHeaders();
//			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//			return ResponseEntity.ok().headers(header).body(list);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
