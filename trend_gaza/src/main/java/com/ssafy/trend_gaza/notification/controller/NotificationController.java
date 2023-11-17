package com.ssafy.trend_gaza.notification.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.notification.dto.NotificationResponse;
import com.ssafy.trend_gaza.notification.entity.Notification;
import com.ssafy.trend_gaza.notification.service.NotificationService;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody NotificationResponse notificationResponse) {
		try {
			notificationService.register(notificationResponse);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam String notificationIdx) {
		try {
			notificationService.delete(notificationIdx);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/{userIdTo}")
	public ResponseEntity<?> list(@PathVariable String userIdTo) {
		try {
			List<Notification> list = notificationService.list(userIdTo);
			if(list != null) {
				return new ResponseEntity<List<Notification>>(list, HttpStatus.OK);
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
