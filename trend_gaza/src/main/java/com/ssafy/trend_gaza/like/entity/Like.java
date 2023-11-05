package com.ssafy.trend_gaza.like.entity;

public class Like {

	private Integer likeIdx;
	private Integer reviewIdx;
	private String userId;
	
	public Like() {}

	public Like(Integer likeIdx, Integer reviewIdx, String userId) {
		super();
		this.likeIdx = likeIdx;
		this.reviewIdx = reviewIdx;
		this.userId = userId;
	}

	
	public Integer getLikeIdx() {
		return likeIdx;
	}

	public Integer getReviewIdx() {
		return reviewIdx;
	}

	public String getUserId() {
		return userId;
	}

	
	@Override
	public String toString() {
		return "Like [likeIdx=" + likeIdx + ", reviewIdx=" + reviewIdx + ", userId=" + userId + "]";
	}
	
}
