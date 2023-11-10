package com.ssafy.trend_gaza.review.entity;

public class Review {

	private  Integer reviewIdx;
	private  String score;
	private  String title;
	private  String content;
	private  String companion;
	private  String startDate;
	private  String endDate;
	private  String registerDate;
	private  String modifyDate;
	private  String userId;
	private  Integer contentId;
	
	public Review() {}

	public Review(Integer reviewIdx, String score, String title, String content, String companion, String startDate,
			String endDate, String registerDate, String modifyDate, String userId, Integer contentId) {
		super();
		this.reviewIdx = reviewIdx;
		this.score = score;
		this.title = title;
		this.content = content;
		this.companion = companion;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registerDate = registerDate;
		this.modifyDate = modifyDate;
		this.userId = userId;
		this.contentId = contentId;
	}

	public Integer getReviewIdx() {
		return reviewIdx;
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

	public String getRegisterDate() {
		return registerDate;
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
		return "Review [reviewIdx=" + reviewIdx + ", score=" + score + ", title=" + title + ", content=" + content
				+ ", companion=" + companion + ", startDate=" + startDate + ", endDate=" + endDate + ", registerDate="
				+ registerDate + ", modifyDate=" + modifyDate + ", userId=" + userId + ", contentId=" + contentId + "]";
	}
	
}
