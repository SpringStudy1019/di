package com.ssafy.trend_gaza.like.service;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.like.dto.LikeRequest;

@Service
public class LikeServiceImpl implements LikeService {

	@Override
	public void onLike(LikeRequest likeRequest) throws Exception {
		// 좋아요를 이미 했는지를 체크. 
		// select query 쓰기. 
		// 좋아요 등록: 좋아요가 없으면 좋아요를 할 수 있다. 좋아요가 있으면 좋아요를 할 수 없다.  
		// 좋아요 취소: 좋아요가 있어야 좋아요 취소를 할 수 있다. 좋아요가 없으면 좋아요 취소를 할 수 없다. 
		
	}

}
