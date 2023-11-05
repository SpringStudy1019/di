package com.ssafy.trend_gaza.like.service;

import com.ssafy.trend_gaza.like.dto.LikeRequest;

public interface LikeService {

	/* 좋아요 등록 */
	void onLike(LikeRequest likeRequest) throws Exception;
	
}
