package com.ssafy.trend_gaza.review.dto;

public class ReviewRegisterRequest {

	private final String score;
	private final String title;
	private final String content;
	private final String registerDate;
	private final String userId;
	private final Integer contentId;
	
	public ReviewRegisterRequest(String score, String title, String content, String registerDate, String userId,
			Integer contentId) {
		super();
		this.score = score;
		this.title = title;
		this.content = content;
		this.registerDate = registerDate;
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

	public String getRegisterDate() {
		return registerDate;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getContentId() {
		return contentId;
	}


	@Override
	public String toString() {
		return "RegisterRequest [score=" + score + ", title=" + title + ", content=" + content + ", registerDate="
				+ registerDate + ", userId=" + userId + ", contentId=" + contentId + "]";
	}
	
	
}
