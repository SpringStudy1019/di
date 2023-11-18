package com.ssafy.trend_gaza.bookmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.bookmark.dto.BookmarkRequest;
import com.ssafy.trend_gaza.bookmark.service.BookmarkService;

@RestController
@RequestMapping("/bookmark")
@CrossOrigin("*")
public class BookmarkController {

	private BookmarkService bookmarkService;

	public BookmarkController(BookmarkService bookmarkService) {
		super();
		this.bookmarkService = bookmarkService;
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody BookmarkRequest bookmarkRequest) {
		try {
			int result = bookmarkService.register(bookmarkRequest);
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.CREATED);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping(value="/{userId}")
	public ResponseEntity<?> delete(@PathVariable String userId, @RequestParam int contentId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("contentId", contentId);
		try {
			int result = bookmarkService.delete(param);
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.OK);				
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value="/{userId}")
	public ResponseEntity<?> bookmarkList(@PathVariable String userId) {
		try {
			List<AttractionInfo> list = bookmarkService.list(userId);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<AttractionInfo>>(list, HttpStatus.OK);				
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
