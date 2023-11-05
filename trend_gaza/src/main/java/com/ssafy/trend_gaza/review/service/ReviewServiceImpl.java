package com.ssafy.trend_gaza.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.review.dto.ReviewModifyRequest;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.entity.Review;
import com.ssafy.trend_gaza.review.repository.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewMapper reviewMapper;
	
	public ReviewServiceImpl(ReviewMapper reviewMapper) {
		super();
		this.reviewMapper = reviewMapper;
	}

	
	@Override
	public void register(ReviewRegisterRequest reviewRegisterRequest) throws Exception {
		reviewMapper.register(reviewRegisterRequest);
		
	}

	@Override
	public List<Review> list() throws Exception {
		return reviewMapper.list();
	}

	@Override
	public void modify(ReviewModifyRequest ModifyRequest) throws Exception {
		reviewMapper.modify(ModifyRequest);
		
	}

	@Override
	public void delete(String reviewIdx) throws Exception {
		reviewMapper.delete(reviewIdx);
		
	}

}
