package com.ssafy.trend_gaza.follow.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;

public interface FollowService {

	/* 팔로우 등록 */
	int onFollow(FollowRequest followRequest) throws Exception;
	
	/* 팔로우 취소 */ 
	int offFollow(Map<String, String> param) throws Exception;

	/* 팔로우 목록 */
	List<String> listFollowers(String followeeId) throws Exception;

}
