package com.ssafy.trend_gaza.follow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.attraction.controller.AttractionController;
import com.ssafy.trend_gaza.follow.controller.FollowController;
import com.ssafy.trend_gaza.follow.dto.FollowRequest;
import com.ssafy.trend_gaza.follow.entity.Follow;
import com.ssafy.trend_gaza.follow.repository.FollowMapper;

import lombok.extern.slf4j.Slf4j;

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
	public int offFollow(FollowRequest followRequest) throws Exception {
		int result = 0;
		Follow follow = followMapper.findFollow(followRequest);
		if (follow != null) {
			result = followMapper.offFollow(followRequest);
		}
		return result;
	}

	@Override
	public List<String> listFollowers(String followeeId) throws Exception {
		return followMapper.listFollowers(followeeId);
	}

	
}
