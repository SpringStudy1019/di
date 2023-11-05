package com.ssafy.trend_gaza.review.dto;

public class ReviewModifyRequest {

	private final String score;
	private final String title;
	private final String content;
	private final String modifyDate;
	private final String userId;
	private final Integer contentId;
	
	public ReviewModifyRequest(String score, String title, String content, String modifyDate, String userId,
			Integer contentId) {
		super();
		this.score = score;
		this.title = title;
		this.content = content;
		this.modifyDate = modifyDate;
		this.userId = userId;
		this.contentId = contentId;
	}

	
	public String getScore() {
		return score;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getContentId() {
		return contentId;
	}

	
	@Override
	public String toString() {
		return "ModifyRequest [score=" + score + ", title=" + title + ", content=" + content + ", modifyDate="
				+ modifyDate + ", userId=" + userId + ", contentId=" + contentId + "]";
	}
	
	
}
