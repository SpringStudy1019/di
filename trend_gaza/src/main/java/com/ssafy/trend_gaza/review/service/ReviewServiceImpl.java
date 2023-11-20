package com.ssafy.trend_gaza.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.review.dto.ReviewModifyResponse;
import com.ssafy.trend_gaza.review.dto.ReviewRegisterRequest;
import com.ssafy.trend_gaza.review.dto.ReviewResponse;
import com.ssafy.trend_gaza.review.dto.ScoreCountResponse;
import com.ssafy.trend_gaza.review.entity.FileInfo;
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
		Review review = Review.builder()
				.score(reviewRegisterRequest.getScore())
				.title(reviewRegisterRequest.getTitle())
				.content(reviewRegisterRequest.getContent())
				.companion(reviewRegisterRequest.getCompanion())
				.startDate(reviewRegisterRequest.getStartDate())
				.endDate(reviewRegisterRequest.getEndDate())
				.userId(reviewRegisterRequest.getUserId())
				.contentId(reviewRegisterRequest.getContentId())
				.fileInfos(reviewRegisterRequest.getFileInfos())
				.build();
		reviewMapper.register(review);
		List<FileInfo> fileInfos = reviewRegisterRequest.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			reviewMapper.registerFile(review);
		}
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
	public Review view(int reviewIdx) throws Exception {
		return reviewMapper.view(reviewIdx);
	}

	@Override
	public Review getModify(int reviewIdx) throws Exception {
		return reviewMapper.getModify(reviewIdx);
	}
	
	@Override
	public void modify(ReviewModifyResponse modifyResponse) throws Exception {
		reviewMapper.modify(modifyResponse);
		
	}

	@Override
	public void delete(int reviewIdx) throws Exception {
		reviewMapper.delete(reviewIdx);
	}


	@Override
	public List<FileInfo> getReviewImg(int reviewIdx) {
		return reviewMapper.getReviewImage(reviewIdx);
	}


	@Override
	public List<AttractionInfo> mostReviews() throws Exception {
		return reviewMapper.mostReviews();
	}


	@Override
	public ScoreCountResponse countScores(int contentId) throws Exception {
		return reviewMapper.countScores(contentId);
	}


	@Override
	public List<Review> getReviewsByContentId(int contentId) throws Exception {
		return reviewMapper.getReviewsByContentId(contentId);
	}


}