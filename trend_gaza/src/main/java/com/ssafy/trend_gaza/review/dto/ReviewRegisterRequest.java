package com.ssafy.trend_gaza.review.dto;

import lombok.Builder;

@Builder
public class ReviewRegisterRequest {

	private  String score;
	private  String title;
	private  String content;
	private  String userId;
	private  Integer contentId;
	
//	private final String score;
//	private final String title;
//	private final String content;
//	private final String registerDate;
//	private final String userId;
//	private final Integer contentId;
	
	public ReviewRegisterRequest() {}	
	
	public ReviewRegisterRequest(String score, String title, String content, String userId, Integer contentId) {
		super();
		this.score = score;
		this.title = title;
		this.content = content;
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

	
	public String getUserId() {
		return userId;
	}

	public Integer getContentId() {
		return contentId;
	}

	
	@Override
	public String toString() {
		return "ReviewRegisterRequest [score=" + score + ", title=" + title + ", content=" + content + ", userId="
				+ userId + ", contentId=" + contentId + "]";
	}

}
