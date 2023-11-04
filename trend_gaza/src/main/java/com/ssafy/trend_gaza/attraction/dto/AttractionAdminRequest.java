package com.ssafy.trend_gaza.attraction.dto;

import lombok.Builder;

@Builder
public class AttractionAdminRequest {

	private Integer attractionId;
	private Integer contentTypeId;
	private String title;
	private String address;
	private String tel;
	private String defaultImg;
	private double latitude;
	private double longitude;
	private Integer sidoCode;
	private Integer gugunCode;

	
	public AttractionAdminRequest() {
	}

	public AttractionAdminRequest(Integer attractionId, Integer contentTypeId, String title, String address, String tel,
			String defaultImg, double latitude, double longitude, Integer gugunCode, Integer sidoCode) {
		super();
		this.attractionId = attractionId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.address = address;
		this.tel = tel;
		this.defaultImg = defaultImg;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gugunCode = gugunCode;
		this.sidoCode = sidoCode;
	}


	public Integer getAttractionId() {
		return attractionId;
	}

	public Integer getContentTypeId() {
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

	public Integer getSidoCode() {
		return sidoCode;
	}

	public Integer getGugunCode() {
		return gugunCode;
	}

}
