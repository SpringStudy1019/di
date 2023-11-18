package com.ssafy.trend_gaza.bookmark.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.bookmark.dto.BookmarkRequest;
import com.ssafy.trend_gaza.bookmark.entity.Bookmark;

@Mapper
public interface BookmarkMapper {

	/* 북마크 등록 */
	int register(BookmarkRequest bookmarkRequest) throws SQLException;
	
	/* 북마크 삭제 */
	int delete(Map<String, Object> param) throws SQLException;
	
	/* 내가 찜한 여행지 조회 */
	List<AttractionInfo> list(String userId) throws SQLException;
	
	/* 이미 찜한 여행지인지 체크 */
	Bookmark check(BookmarkRequest bookmarkRequest) throws SQLException;
}
