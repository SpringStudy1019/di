package com.ssafy.trend_gaza.follow.dto;

import lombok.Builder;

@Builder
public class FollowRequest {

	private Integer followIdx;
	private String followeeId; 
	private String followerId; 
	
	public FollowRequest() {}

	public FollowRequest(Integer followIdx, String followeeId, String followerId) {
		super();
		this.followIdx = followIdx;
		this.followeeId = followeeId;
		this.followerId = followerId;
	}

	
	public Integer getFollowIdx() {
		return followIdx;
	}

	public String getFolloweeId() {
		return followeeId;
	}

	public String getFollowerId() {
		return followerId;
	}

}
