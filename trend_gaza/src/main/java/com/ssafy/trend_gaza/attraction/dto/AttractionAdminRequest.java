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

	public Integer getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(Integer attractionId) {
		this.attractionId = attractionId;
	}

	public Integer getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(Integer sidoCode) {
		this.sidoCode = sidoCode;
	}

	public Integer getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(Integer gugunCode) {
		this.gugunCode = gugunCode;
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

}
