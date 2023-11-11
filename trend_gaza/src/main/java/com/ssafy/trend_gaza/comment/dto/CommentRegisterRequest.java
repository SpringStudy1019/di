package com.ssafy.trend_gaza.comment.dto;

public class CommentRegisterRequest {

	private Integer commentIdx;
	private String content;
	private String registerDate;
	private String userId;
	private Integer reviewIdx;
	
	public CommentRegisterRequest() {}
	
	public CommentRegisterRequest(Integer commentIdx, String content, String registerDate, String userId,
			Integer reviewIdx) {
		super();
		this.commentIdx = commentIdx;
		this.content = content;
		this.registerDate = registerDate;
		this.userId = userId;
		this.reviewIdx = reviewIdx;
	}
	
	
	public Integer getCommentIdx() {
		return commentIdx;
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
