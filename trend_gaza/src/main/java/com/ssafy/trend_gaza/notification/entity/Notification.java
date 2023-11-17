package com.ssafy.trend_gaza.notification.entity;

import java.time.LocalDateTime;

import com.ssafy.trend_gaza.user.entity.User;

import lombok.Builder;

@Builder
public class Notification {

	private Long id;
	private User user;
	private String message;
	private LocalDateTime pushTime;
	private PushStatus pushStatus;
	private PushCase pushCase;
	private Long pathId;
	
	public Notification() {}
	
	public Notification(Long id, User user, String message, LocalDateTime pushTime, PushStatus pushStatus,
			PushCase pushCase, Long pathId) {
		super();
		this.id = id;
		this.user = user;
		this.message = message;
		this.pushTime = pushTime;
		this.pushStatus = pushStatus;
		this.pushCase = pushCase;
		this.pathId = pathId;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getPushTime() {
		return pushTime;
	}

	public PushStatus getPushStatus() {
		return pushStatus;
	}

	public PushCase getPushCase() {
		return pushCase;
	}

	public Long getPathId() {
		return pathId;
	}
	
	public boolean isPushable(LocalDateTime now) {
		return pushStatus == PushStatus.IN_COMPLETE 
				&& (pushTime.isBefore(now) || pushTime.isEqual(now));
	}
	
	
}
