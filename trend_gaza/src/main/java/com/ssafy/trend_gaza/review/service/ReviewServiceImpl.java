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
	public List<Review> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		
		String pgnoString = map.get("pgno");
	    int pgno = 1; // Default value in case "pgno" is not provided or invalid
	    if (pgnoString != null) pgno = Integer.parseInt(pgnoString);
	    
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return reviewMapper.list(param);
	}
	
	@Override
	public Review view(String reviewIdx) throws Exception {
		return reviewMapper.view(reviewIdx);
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
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
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
