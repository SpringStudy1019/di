package com.ssafy.trend_gaza.review.entity;

import lombok.Builder;

@Builder
public class FileInfo {

	private Integer fileIdx;
	private String saveFile;
	private Integer reviewIdx;
	
	public FileInfo() {}

	public FileInfo(Integer fileIdx, String saveFile, Integer reviewIdx) {
		super();
		this.fileIdx = fileIdx;
		this.saveFile = saveFile;
		this.reviewIdx = reviewIdx;
	}

	public Integer getFileIdx() {
		return fileIdx;
	}

	public String getSaveFile() {
		return saveFile;
	}

	public Integer getReviewIdx() {
		return reviewIdx;
	}

}
