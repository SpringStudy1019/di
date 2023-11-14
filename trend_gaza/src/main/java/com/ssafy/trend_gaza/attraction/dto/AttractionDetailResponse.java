package com.ssafy.trend_gaza.attraction.dto;

import lombok.Builder;

@Builder
public class AttractionDetailResponse {
	
	private int contentId;
	private int contentTypeId;
	private String title;
	private String address;
	private String tel;
	private String defaultImg;
	private double latitude;
	private double longitude;
	
	
	public AttractionDetailResponse() {}
	
	public AttractionDetailResponse(int contentId, int contentTypeId, String title, String address, String tel,
			String defaultImg, double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.address = address;
		this.tel = tel;
		this.defaultImg = defaultImg;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public int getContentId() {
		return contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	
	@Override
	public String toString() {
		return "AttractionDetailResponse [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title="
				+ title + ", address=" + address + ", tel=" + tel + ", defaultImg=" + defaultImg + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
}
