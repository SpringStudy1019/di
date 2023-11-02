package com.ssafy.trend_gaza.attraction.dto;

public class AttractionDetailResponse {
	
	private int contentId;
	private int contentTypeId;
	private String title;
	private String address;
	private String tel;
	private String defaultImg;
	private double latitude;
	private double longitude;
	
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
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
	
	
	@Override
	public String toString() {
		return "AttractionDetailResponse [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title="
				+ title + ", address=" + address + ", tel=" + tel + ", defaultImg=" + defaultImg + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
}
