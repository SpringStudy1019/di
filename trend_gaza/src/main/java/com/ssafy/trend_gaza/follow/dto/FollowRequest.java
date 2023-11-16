package com.ssafy.trend_gaza.follow.dto;

public class FollowRequest {

	private String followeeId; // star
	private String followerId; // fan
	
	public FollowRequest() {}
	
	public FollowRequest(String followeeId, String followerId) {
		super();
		this.followeeId = followeeId;
		this.followerId = followerId;
	}

	
	public String getFolloweeId() {
		return followeeId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFolloweeId(String followeeId) {
		this.followeeId = followeeId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

}
