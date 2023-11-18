package com.ssafy.trend_gaza.bookmark.dto;

import lombok.Builder;

@Builder
public class BookmarkRequest {
	
	private String userId;
	private Integer attractionId;
	
	
	public BookmarkRequest() {}

	public BookmarkRequest(String userId, Integer attractionId) {
		super();
		this.userId = userId;
		this.attractionId = attractionId;
	}

	
	public String getUserId() {
		return userId;
	}

	public Integer getAttractionId() {
		return attractionId;
	}
	
}
