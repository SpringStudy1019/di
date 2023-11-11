package com.ssafy.trend_gaza.comment.dto;

public class CommentRegisterRequest {

	private String content;
	private String registerDate;
	private String userId;
	private Integer reviewIdx;
	
	public CommentRegisterRequest() {}

	public CommentRegisterRequest(String content, String registerDate, String userId, Integer reviewIdx) {
		super();
		this.content = content;
		this.registerDate = registerDate;
		this.userId = userId;
		this.reviewIdx = reviewIdx;
	}

	
	public String getContent() {
		return content;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getReviewIdx() {
		return reviewIdx;
	}
	
}
