package com.ssafy.trend_gaza.follow.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.user.entity.User;

public interface FollowService {

	/* 팔로우 등록 */
	int onFollow(FollowRequest followRequest) throws Exception;
	
	/* 팔로우 취소 */ 
	int offFollow(Map<String, String> param) throws Exception;

	/* 팔로우 목록 */
	List<String> listFollowers(String followeeId) throws Exception;
	
	/* 팔로워 숫자 */
	int followCount(String followerId) throws Exception;
	
	/* 연관 친구들 불러오기 */
	List<User> listRelated(FollowRequest followRequest) throws Exception;

}
