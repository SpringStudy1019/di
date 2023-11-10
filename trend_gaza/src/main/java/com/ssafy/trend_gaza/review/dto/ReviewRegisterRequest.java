package com.ssafy.trend_gaza.review.dto;

import lombok.Builder;

@Builder
public class ReviewRegisterRequest {

	private  String score;
	private  String title;
	private  String content;
	private  String companion;
	private  String startDate;
	private  String endDate;
	private  String userId;
	private  Integer contentId;
	
	public ReviewRegisterRequest() {}

	public ReviewRegisterRequest(String score, String title, String content, String companion, String startDate,
			String endDate, String userId, Integer contentId) {
		super();
		this.score = score;
		this.title = title;
		this.content = content;
		this.companion = companion;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getCompanion() {
		return companion;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getContentId() {
		return contentId;
	}

	
	@Override
	public String toString() {
		return "ReviewRegisterRequest [score=" + score + ", title=" + title + ", content=" + content + ", companion="
				+ companion + ", startDate=" + startDate + ", endDate=" + endDate + ", userId=" + userId
				+ ", contentId=" + contentId + "]";
	}
	
	

	
}
