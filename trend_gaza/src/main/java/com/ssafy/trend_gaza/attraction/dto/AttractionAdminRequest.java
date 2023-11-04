package com.ssafy.trend_gaza.attraction.dto;

public class AttractionAdminRequest {
	
	private final Integer attractionId;
	private final Integer contentTypeId;
	private final String title;
	private final String address;
	private final String tel;
	private final String defaultImg;
	private final double latitude;
	private final double longitude;
	private final Integer sidoCode;
	private final Integer gugunCode;
	
	
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
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Integer getSidoCode() {
		return sidoCode;
	}
	public Integer getGugunCode() {
		return gugunCode;
	}
	
}
