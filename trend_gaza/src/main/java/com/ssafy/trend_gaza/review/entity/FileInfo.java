package com.ssafy.trend_gaza.review.entity;

import lombok.Builder;

@Builder
public class FileInfo {

	private String saveFile;
	
	public FileInfo() {}
	
	
	public FileInfo(String saveFile) {
		super();
		this.saveFile = saveFile;
	}
	
	public String getSaveFile() {
		return saveFile;
	}

}
