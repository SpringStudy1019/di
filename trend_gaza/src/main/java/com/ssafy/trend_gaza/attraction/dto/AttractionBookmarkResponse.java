package com.ssafy.trend_gaza.attraction.dto;

import lombok.Builder;

@Builder
public class AttractionBookmarkResponse {
	
	private String title;
	private Integer contentId;
	private String address;
	private String imgUrl;
	private Double latitude;
	private Double longitude;
	
	public AttractionBookmarkResponse() {}

	public AttractionBookmarkResponse(String title, Integer contentId, String address, String imgUrl, Double latitude,
			Double longitude) {
		super();
		this.title = title;
		this.contentId = contentId;
		this.address = address;
		this.imgUrl = imgUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getTitle() {
		return title;
	}

	public Integer getContentId() {
		return contentId;
	}

	public String getAddress() {
		return address;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
	
	
	
}
