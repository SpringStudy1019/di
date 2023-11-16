package com.ssafy.trend_gaza.follow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.follow.dto.FollowRequest;
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
		return followMapper.onFollow(followRequest);
	}

	@Override
	public int offFollow(FollowRequest followRequest) throws Exception {
		return followMapper.offFollow(followRequest);
	}

	@Override
	public List<String> listFollowers(String followeeId) throws Exception {
		return followMapper.listFollowers(followeeId);
	}

	
}
