package com.ssafy.trend_gaza.follow.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.follow.entity.Follow;
import com.ssafy.trend_gaza.user.entity.User;

@Mapper
public interface FollowMapper {
	
	/* 팔로우 등록 */
	int onFollow(FollowRequest followRequest) throws SQLException;
	
	/* 팔로우 취소 */ 
	int offFollow(Map<String, String> param) throws SQLException;

	/* 내가 팔로우하고 있는 사람들 목록 */
	List<User> listFollowers(String followeeId) throws SQLException;
	
	/* 팔로잉 여부 체크 */
	Follow findFollow(FollowRequest followRequest) throws SQLException;
	
	/* 팔로워 숫자 */
	int followCount(String followerId) throws SQLException;
	
	/* 연관 친구들 불러오기 */
	List<User> listRelated(@Param("currentUserId") String currentUserId, @Param("followeeId") String followeeId) throws SQLException;
}
