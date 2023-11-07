package com.ssafy.trend_gaza.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.review.dto.ReviewModifyRequest;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.entity.Review;
import com.ssafy.trend_gaza.review.repository.ReviewMapper;
import com.ssafy.trend_gaza.util.PageNavigation;
import com.ssafy.trend_gaza.util.SizeConstant;

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
	public List<Review> list(Map<String, String> param) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String word = param.get("word");
		
		map.put("word", word == null ? "" : word);
		
		return reviewMapper.list(param);
	}

	@Override
	public void modify(ReviewModifyRequest ModifyRequest) throws Exception {
		reviewMapper.modify(ModifyRequest);
		
	}

	@Override
	public void delete(String reviewIdx) throws Exception {
		reviewMapper.delete(reviewIdx);
		
	}


	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, String> param = new HashMap<String, String>();
		String key = map.get("key");
		if ("userId".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = reviewMapper.getTotalReviewCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

}
