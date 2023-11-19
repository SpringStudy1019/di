package com.ssafy.trend_gaza.attraction.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.dto.AttractionAutoSearchResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionPlanResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.bookmark.dto.BookmarkRequest;
import com.ssafy.trend_gaza.bookmark.entity.Bookmark;

@Mapper
public interface AttractionMapper {
	List<AttractionInfo> searchAttractions(Map<String, Object> param);
	void registerAdminAttraction(AttractionAdminRequest attractionAdminRequest);
	void registerAttractionDetail(AttractionAdminRequest attractionAdminRequest);
	AttractionDetailResponse getAttractionDetail(int id);
	List<AttractionAutoSearchResponse> attractionNameList();
	List<AttractionInfo> searchByCategory(Map<String, Object> param);
	List<AttractionPlanResponse> attractionPlan(Map<String, Object> param);
	
	/* 북마크 */
	Bookmark findBookmark(BookmarkRequest bookmarkRequest) throws SQLException;
	int onBookmark(BookmarkRequest bookmarkRequest) throws SQLException;
}
