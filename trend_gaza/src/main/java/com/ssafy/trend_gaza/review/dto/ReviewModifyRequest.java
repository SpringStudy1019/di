package com.ssafy.trend_gaza.review.dto;

import lombok.Builder;

@Builder
public class ReviewModifyRequest {

	private  Integer reviewIdx;
	private  String score;
	private  String title;
	private  String content;
	
//	private final String score;
//	private final String title;
//	private final String content;
//	private final String userId;
//	private final Integer contentId;
	
	public ReviewModifyRequest() {}

	public ReviewModifyRequest(Integer reviewIdx, String score, String title, String content) {
		super();
		this.reviewIdx = reviewIdx;
		this.score = score;
		this.title = title;
		this.content = content;
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

	
	@Override
	public String toString() {
		return "ReviewModifyRequest [reviewIdx=" + reviewIdx + ", score=" + score + ", title=" + title + ", content="
				+ content + "]";
	}

}
