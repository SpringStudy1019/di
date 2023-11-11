package com.ssafy.trend_gaza.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.comment.dto.CommentModifyResponse;
import com.ssafy.trend_gaza.comment.dto.CommentRegisterRequest;
import com.ssafy.trend_gaza.comment.entity.Comment;
import com.ssafy.trend_gaza.comment.service.CommentService;

@RestController
@RequestMapping("/comment")
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
	
	@GetMapping(value = "/view/{reviewIdx}")
	public ResponseEntity<?> list(@PathVariable int reviewIdx) {
		try {
			List<Comment> list = commentService.list(reviewIdx);
			if (list != null) {
				return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/getModify/{commentIdx}")
	public ResponseEntity<?> getModify(@PathVariable int commentIdx) {
		try {
			Comment comment = commentService.getModify(commentIdx);
			if (comment != null) {
				return new ResponseEntity<Comment>(comment, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> modify(@RequestBody CommentModifyResponse commentModifyResponse) {
		try {
			commentService.modify(commentModifyResponse);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping(value = "/{commentIdx}")
	public ResponseEntity<?> delete(@PathVariable int commentIdx) {
		try {
			commentService.delete(commentIdx);
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
























