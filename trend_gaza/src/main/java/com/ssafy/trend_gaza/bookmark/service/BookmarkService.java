package com.ssafy.trend_gaza.bookmark.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.bookmark.dto.BookmarkRequest;

public interface BookmarkService {

	/* 북마크 등록 */
	int register(BookmarkRequest bookmarkRequest) throws Exception;
	
	/* 북마크 삭제 */
	int delete(Map<String, Object> param) throws Exception;
	
	/* 내가 찜한 여행지 조회 */
	List<AttractionInfo> list(String userId) throws Exception;
}
