package com.ssafy.trend_gaza.follow.entity;

public class Follow {

	private Integer followIdx;
	private String followeeId; // star
	private String followerId; // fan
	
	public Follow() {}

	public Follow(Integer followIdx, String followeeId, String followerId) {
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
