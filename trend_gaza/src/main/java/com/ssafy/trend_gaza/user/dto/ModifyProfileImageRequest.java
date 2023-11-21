package com.ssafy.trend_gaza.user.dto;

import lombok.Builder;

@Builder
public class ModifyProfileImageRequest {
	
	private String imgUrl;
	
	public ModifyProfileImageRequest() {}
	
	public ModifyProfileImageRequest(String imgUrl) {
		super();
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
