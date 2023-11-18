package com.ssafy.trend_gaza.review.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.review.dto.ReviewModifyResponse;
import com.ssafy.trend_gaza.review.entity.FileInfo;
import com.ssafy.trend_gaza.review.entity.Review;

@Mapper
public interface ReviewMapper {

	/* 리뷰 등록 */ 
	void register(Review registerRequest) throws SQLException;
	
	/* 이미지 업로드 */
	void registerFile(Review registerRequest) throws Exception;
	
	/* 리뷰 전체 조회 (+검색) */
	List<Review> list(Map<String, Object> param) throws SQLException;
	
	/* 리뷰 카운트 */
	int getTotalReviewCount(Map<String, Object> param) throws SQLException;

	/* 리뷰 상세 조회 */
	Review view(int reviewIdx) throws SQLException;
	
	/* 리뷰 이미지 조회 */
	List<FileInfo> getReviewImage(int reviewIdx);
	
	/* 리뷰 수정 */
	Review getModify(int reviewIdx) throws SQLException;
	void modify(ReviewModifyResponse modifyResponse) throws SQLException;
	
	/* 리뷰 삭제 */ 
	void delete(String reviewIdx) throws SQLException;
	
	/* 사용자들이 가장 많이 리뷰를 남긴 여행지 조회 */ 
	List<AttractionInfo> mostReviews() throws SQLException;
	
}