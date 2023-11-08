package com.ssafy.trend_gaza.like.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.like.dto.LikeRequest;
import com.ssafy.trend_gaza.like.entity.Like;
import com.ssafy.trend_gaza.like.repository.LikeMapper;

@Service
public class LikeServiceImpl implements LikeService {

private LikeMapper likeMapper;
	
	public LikeServiceImpl(LikeMapper likeMapper) {
		super();
		this.likeMapper = likeMapper;
	}

	@Override
	public int onLike(LikeRequest likeRequest) throws Exception {
		// 좋아요 등록: 좋아요가 없으면 좋아요를 할 수 있다. 좋아요가 있으면 좋아요를 할 수 없다.  
		int result = 0;
		Like like = likeMapper.findLike(likeRequest);
		if (like == null) { 
			result = likeMapper.onLike(likeRequest);
		} 
		return result;
		
	}

	@Override
	public int offLike(LikeRequest likeRequest) throws Exception {
		// 좋아요 취소: 좋아요가 있어야 좋아요 취소를 할 수 있다. 좋아요가 없으면 좋아요 취소를 할 수 없다. 
		int result = 0;
		Like like = likeMapper.findLike(likeRequest);
		if (like != null) {			
			result = likeMapper.offLike(likeRequest);
		}
		return result;
	}

	@Override
	public int count(int reviewIdx) throws SQLException {
		return likeMapper.count(reviewIdx);
	}

	@Override
	public List<String> selectUsers(int reviewIdx) throws Exception {
		return likeMapper.selectUsers(reviewIdx);
	}

}
