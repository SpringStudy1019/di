package com.ssafy.trend_gaza.review.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.review.dto.ReviewModifyResponse;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.entity.Review;

@Mapper
public interface ReviewMapper {

	/* 리뷰 등록 */ 
	void register(ReviewRegisterRequest registerRequest) throws SQLException;
	
	/* 리뷰 전체 조회 (+검색) */
	List<Review> list(Map<String, Object> param) throws SQLException;
	
	/* 리뷰 카운트 */
	int getTotalReviewCount(Map<String, Object> param) throws SQLException;

	/* 리뷰 상세 조회 */
	Review view(int reviewIdx) throws SQLException;
	
	/* 리뷰 수정 */
	Review getModify(int reviewIdx) throws SQLException;
	void modify(ReviewModifyResponse modifyResponse) throws SQLException;
	
	/* 리뷰 삭제 */ 
	void delete(String reviewIdx) throws SQLException;
	
}