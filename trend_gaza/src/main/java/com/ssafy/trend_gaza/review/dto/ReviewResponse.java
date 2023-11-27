package com.ssafy.trend_gaza.review.dto;


import com.ssafy.trend_gaza.review.entity.Review;
import java.util.List;

public class ReviewResponse {

    private List<Review> reviews;
    private Integer currentPage;
    private Integer totalPageCount;

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
