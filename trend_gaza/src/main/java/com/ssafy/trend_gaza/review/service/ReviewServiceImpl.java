package com.ssafy.trend_gaza.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.review.dto.ReviewModifyRequest;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.dto.ReviewResponse;
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
	public ReviewResponse list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "user_id");
		List<Review> list = reviewMapper.list(param);
		int totalArticleCount = reviewMapper.getTotalReviewCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		ReviewResponse reviewResponse = new ReviewResponse();
		reviewResponse.setReviews(list);
		reviewResponse.setCurrentPage(currentPage);
		reviewResponse.setTotalPageCount(totalPageCount);

		return reviewResponse;
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

}
