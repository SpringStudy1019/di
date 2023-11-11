package com.ssafy.trend_gaza.review.service;

import java.util.Map;

import com.ssafy.trend_gaza.review.dto.ReviewModifyRequest;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.dto.ReviewResponse;
import com.ssafy.trend_gaza.review.entity.Review;

public interface ReviewService {

	/* 리뷰 등록 */ 
	void register(ReviewRegisterRequest registerRequest) throws Exception;
	
	/* 리뷰 전체 조회 (+검색) */
	ReviewResponse list(Map<String, String> map) throws Exception;
	
	/* 리뷰 상세 조회 */
	Review view(String reviewIdx) throws Exception;
	
	/* 리뷰 수정 */
	void modify(ReviewModifyRequest ModifyRequest) throws Exception;
	
	/* 리뷰 삭제 */ 
	void delete(String reviewIdx) throws Exception;
	
}
