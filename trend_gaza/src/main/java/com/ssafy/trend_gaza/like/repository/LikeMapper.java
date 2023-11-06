package com.ssafy.trend_gaza.like.repository;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.like.dto.LikeRequest;
import com.ssafy.trend_gaza.like.entity.Like;

@Mapper
public interface LikeMapper {

	/* 좋아요 확인 */ 
	Like findLike(LikeRequest likeRequest) throws SQLException;
	
	/* 좋아요 등록 */
	int onLike(LikeRequest likeRequest) throws SQLException;
	
	/* 좋아요 취소 */ 
	int offLike(LikeRequest likeRequest) throws SQLException;
	
	/* 좋아요 카운트 */ 
	int count(int reviewIdx) throws SQLException;
}
