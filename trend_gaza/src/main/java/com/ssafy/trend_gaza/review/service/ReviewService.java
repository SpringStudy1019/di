package com.ssafy.trend_gaza.review.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.review.dto.ReviewModifyResponse;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.dto.ReviewResponse;
import com.ssafy.trend_gaza.review.dto.ScoreCountResponse;
import com.ssafy.trend_gaza.review.entity.FileInfo;
import com.ssafy.trend_gaza.review.entity.Review;

public interface ReviewService {

	/* 리뷰 등록 */ 
	void register(ReviewRegisterRequest registerRequest) throws Exception;
	
	/* 리뷰 전체 조회 (+검색) */
	ReviewResponse list(Map<String, String> map) throws Exception;
	
	/* 리뷰 상세 조회 */
	Review view(int reviewIdx) throws Exception;
	
	/* 리뷰에 등록된 이미지 조회 */
	List<FileInfo> getReviewImg(int reviewIdx);
	
	/* 리뷰 수정 */
	Review getModify(int reviewIdx) throws Exception;
	void modify(ReviewModifyResponse modifyResponse) throws Exception;
	
	/* 리뷰 삭제 */ 
	void delete(String reviewIdx) throws Exception;
	
	/* 사용자들이 가장 많이 리뷰를 남긴 여행지 조회 */ 
	List<AttractionInfo> mostReviews() throws Exception;
	
	/* 여행지별 점수 카운트 */
	ScoreCountResponse countScores(int contentId) throws Exception;
	
}