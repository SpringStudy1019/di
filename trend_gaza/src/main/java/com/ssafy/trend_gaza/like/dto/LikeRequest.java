package com.ssafy.trend_gaza.like.dto;

import lombok.Builder;

@Builder
public class LikeRequest {

	private String userId;
	private Integer reviewIdx;
	
	public LikeRequest() {
		super();
	}

	public LikeRequest(String userId, Integer reviewIdx) {
		super();
		this.userId = userId;
		this.reviewIdx = reviewIdx;
	}

	
	public String getUserId() {
		return userId;
	}

	public Integer getReviewIdx() {
		return reviewIdx;
	}


	@Override
	public String toString() {
		return "LikeRequest [userId=" + userId + ", reviewIdx=" + reviewIdx + "]";
	}

	
}
