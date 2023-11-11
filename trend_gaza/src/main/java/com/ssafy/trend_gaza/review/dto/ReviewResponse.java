package com.ssafy.trend_gaza.review.dto;

import java.util.List;

import com.ssafy.trend_gaza.review.entity.Review;

import lombok.Builder;

public class ReviewResponse {

	private List<Review> reviews;
	private int currentPage;
	private int totalPageCount;
	
	public ReviewResponse() {}
	
	public ReviewResponse(List<Review> reviews, int currentPage, int totalPageCount) {
		super();
		this.reviews = reviews;
		this.currentPage = currentPage;
		this.totalPageCount = totalPageCount;
	}
	
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

}
