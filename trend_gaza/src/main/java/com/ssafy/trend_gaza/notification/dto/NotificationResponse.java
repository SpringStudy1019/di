package com.ssafy.trend_gaza.notification.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class NotificationResponse {
	
	private Long notificationId;
	private String message;
	private LocalDateTime pushTime;
	private Long pathId;
	private String pushCase;
	
	public NotificationResponse() {}
	
	public NotificationResponse(Long notificationId, String message, LocalDateTime pushTime, Long pathId,
			String pushCase) {
		super();
		this.notificationId = notificationId;
		this.message = message;
		this.pushTime = pushTime;
		this.pathId = pathId;
		this.pushCase = pushCase;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getPushTime() {
		return pushTime;
	}

	public Long getPathId() {
		return pathId;
	}

	public String getPushCase() {
		return pushCase;
	}
	
}
