package com.ssafy.trend_gaza.review.entity;

import java.util.List;

import lombok.Builder;

@Builder
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
	private List<FileInfo> fileInfos;
	
	public Review() {}

	public Review(Integer reviewIdx, String score, String title, String content, String companion, String startDate,
			String endDate, String registerDate, String modifyDate, String userId, Integer contentId,
			List<FileInfo> fileInfos) {
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
		this.fileInfos = fileInfos;
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

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

}
