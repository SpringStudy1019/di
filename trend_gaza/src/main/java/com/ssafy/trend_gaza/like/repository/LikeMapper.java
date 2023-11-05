package com.ssafy.trend_gaza.like.repository;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.like.dto.LikeRequest;

@Mapper
public interface LikeMapper {

	/* 좋아요 등록 */
	void onLike(LikeRequest likeRequest) throws SQLException;
	
	/* 좋아요 취소 */ 
	void offLike() throws SQLException;
}
