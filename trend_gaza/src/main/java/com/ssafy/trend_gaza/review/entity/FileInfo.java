package com.ssafy.trend_gaza.review.entity;

import lombok.Builder;

@Builder
public class FileInfo {

	private String saveFolder;
	private String originalFile;
	private String saveFile;
	
	
	public FileInfo() {}
	
	public FileInfo(String saveFolder, String originalFile, String saveFile) {
		super();
		this.saveFolder = saveFolder;
		this.originalFile = originalFile;
		this.saveFile = saveFile;
	}
	
	
	public String getSaveFolder() {
		return saveFolder;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public String getSaveFile() {
		return saveFile;
	}



	
	
	
	
}
