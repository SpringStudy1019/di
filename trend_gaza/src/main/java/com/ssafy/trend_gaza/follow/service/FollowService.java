package com.ssafy.trend_gaza.follow.service;


import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.follow.entity.Follow;
import com.ssafy.trend_gaza.user.entity.User;
import java.util.List;
import java.util.Map;

public interface FollowService {

    /* 팔로우 등록 */
    int onFollow(FollowRequest followRequest) throws Exception;

    /* 팔로우 취소 */
    int offFollow(Map<String, String> param) throws Exception;

    /* 팔로우 목록 */
    List<User> listFollowers(String followeeId) throws Exception;

    /* 팔로워 숫자 */
    int followCount(String followerId) throws Exception;

    /* 팔로잉 여부 체크 */
    Follow findFollow(FollowRequest followRequest) throws Exception;

    /* 연관 친구들 불러오기 */
    List<User> listRelated(String currentUserId, String followeeId) throws Exception;
}
