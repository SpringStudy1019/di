package com.ssafy.trend_gaza.attraction.dto;

public class AttractionResponse {
	
	private String imgUrl;
	private String title;
	private String contentTypeId;
	private int reviewCnt;
	private String address;
	private float reviewScore;
	
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(float reviewScore) {
		this.reviewScore = reviewScore;
	}
	
	
	@Override
	public String toString() {
		return "AttractionResponse [imgUrl=" + imgUrl + ", title=" + title + ", contentTypeId=" + contentTypeId
				+ ", reviewCnt=" + reviewCnt + ", address=" + address + ", reviewScore=" + reviewScore + "]";
	}
}
