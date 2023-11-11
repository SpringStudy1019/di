package com.ssafy.trend_gaza.comment.dto;

public class CommentModifyResponse {

	private Integer commentIdx;
	private String content;
	private String registerDate;
	private String modifyDate;
	private String userId;
	private Integer reviewIdx;
	
	public CommentModifyResponse() {}
	
	public CommentModifyResponse(Integer commentIdx, String content, String registerDate, String modifyDate,
			String userId, Integer reviewIdx) {
		super();
		this.commentIdx = commentIdx;
		this.content = content;
		this.registerDate = registerDate;
		this.modifyDate = modifyDate;
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
	public String getModifyDate() {
		return modifyDate;
	}
	public String getUserId() {
		return userId;
	}
	public Integer getReviewIdx() {
		return reviewIdx;
	}
	
}
