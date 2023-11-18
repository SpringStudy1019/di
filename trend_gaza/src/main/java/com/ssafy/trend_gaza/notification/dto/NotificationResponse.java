package com.ssafy.trend_gaza.notification.dto;


public class NotificationResponse {
	
	private String message;
	private String pushDate;
	private String pushStatus;
	private String pushCase;
	private String pathId;
	private String userIdFrom;
	private String userIdTo;
	
	public NotificationResponse() {}
	
	public NotificationResponse(String message, String pushDate, String pushStatus, String pushCase, String pathId,
			String userIdFrom, String userIdTo) {
		super();
		this.message = message;
		this.pushDate = pushDate;
		this.pushStatus = pushStatus;
		this.pushCase = pushCase;
		this.pathId = pathId;
		this.userIdFrom = userIdFrom;
		this.userIdTo = userIdTo;
	}

	
	public String getMessage() {
		return message;
	}

	public String getPushDate() {
		return pushDate;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public String getPushCase() {
		return pushCase;
	}

	public String getPathId() {
		return pathId;
	}

	public String getUserIdFrom() {
		return userIdFrom;
	}

	public String getUserIdTo() {
		return userIdTo;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public void setPushCase(String pushCase) {
		this.pushCase = pushCase;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public void setUserIdFrom(String userIdFrom) {
		this.userIdFrom = userIdFrom;
	}

	public void setUserIdTo(String userIdTo) {
		this.userIdTo = userIdTo;
	}
	
}
