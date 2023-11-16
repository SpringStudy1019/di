package com.ssafy.trend_gaza.follow.repository;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;

@Mapper
public interface FollowMapper {
	
	/* 팔로우 등록 */
	int onFollow(FollowRequest followRequest) throws SQLException;
	
	/* 팔로우 취소 */ 
	int offFollow(FollowRequest followRequest) throws SQLException;

	/* 내가 팔로우하고 있는 사람들 목록 */
	List<String> listFollowers(String followeeId) throws SQLException;
}
