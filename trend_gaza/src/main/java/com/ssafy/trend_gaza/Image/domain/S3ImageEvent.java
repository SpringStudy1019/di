package com.ssafy.trend_gaza.Image.domain;

public class S3ImageEvent {
	
	private final String imageName;

	public String getImageName() {
		return imageName;
	}

	public S3ImageEvent(String imageName) {
		super();
		this.imageName = imageName;
	}
	
	
}
