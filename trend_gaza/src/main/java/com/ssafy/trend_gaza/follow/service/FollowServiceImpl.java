package com.ssafy.trend_gaza.follow.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.follow.entity.Follow;
import com.ssafy.trend_gaza.follow.repository.FollowMapper;

@Service
public class FollowServiceImpl implements FollowService {

	private FollowMapper followMapper;
	
	public FollowServiceImpl(FollowMapper followMapper) {
		super();
		this.followMapper = followMapper;
	}

	
	@Override
	public int onFollow(FollowRequest followRequest) throws Exception {
		int result = 0;
		Follow follow = followMapper.findFollow(followRequest);
		System.out.println(follow);
		if (follow == null) {
			result = followMapper.onFollow(followRequest);
		}
		return result;
	}

	@Override
	public int offFollow(Map<String, String> param) throws Exception {
		int result = 0;
		FollowRequest followRequest = new FollowRequest();
		followRequest.setFolloweeId(param.get("followeeId"));
		followRequest.setFollowerId(param.get("followerId"));
		Follow follow = followMapper.findFollow(followRequest);
		if (follow != null) {
			result = followMapper.offFollow(param);
		}
		return result;
	}

	@Override
	public List<String> listFollowers(String followeeId) throws Exception {
		return followMapper.listFollowers(followeeId);
	}


	@Override
	public int followCount(String followerId) throws Exception {
		return followMapper.followCount(followerId);
	}


	@Override
	public List<Follow> listRelated(FollowRequest followRequest) throws Exception {
		return followMapper.listRelated(followRequest);
	}

	
}
