package com.ssafy.trend_gaza.like.service;

import java.util.List;

import com.ssafy.trend_gaza.like.dto.LikeRequest;

public interface LikeService {

	/* 좋아요 등록 */
	int onLike(LikeRequest likeRequest) throws Exception;
	
	/* 좋아요 취소 */ 
	int offLike(LikeRequest likeRequest) throws Exception;
	
	/* 좋아요 카운트 */ 
	int count(int reviewIdx) throws Exception;
	
	/* 좋아요 누른 사용자 표시 */
	List<String> selectUsers(int reviewIdx) throws Exception;
	
}
