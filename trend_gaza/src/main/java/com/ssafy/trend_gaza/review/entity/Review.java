package com.ssafy.trend_gaza.review.entity;

public class Review {

	private  Integer reviewIdx;
	private  String score;
	private  String title;
	private  String content;
	private  String registerDate;
	private  String modifyDate;
	private  String userId;
	private  Integer contentId;
	
//	private final Integer reviewIdx;
//	private final String score;
//	private final String title;
//	private final String content;
//	private final String registerDate;
//	private final String modifyDate;
//	private final String userId;
//	private final Integer contentId;
	
	public Review() {}
	
	public Review(Integer reviewIdx, String score, String title, String content, String registerDate, String modifyDate,
			String userId, Integer contentId) {
		super();
		this.reviewIdx = reviewIdx;
		this.score = score;
		this.title = title;
		this.content = content;
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

	public void setScore(String score) {
		this.score = score;
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
				+ ", registerDate=" + registerDate + ", modifyDate=" + modifyDate + ", userId=" + userId
				+ ", contentId=" + contentId + "]";
	}
	
}
