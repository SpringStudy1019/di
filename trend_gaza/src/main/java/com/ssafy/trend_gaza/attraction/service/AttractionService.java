package com.ssafy.trend_gaza.attraction.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.dto.AttractionAutoSearchResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionDetailResponse;
import com.ssafy.trend_gaza.attraction.dto.AttractionPlanResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;
import com.ssafy.trend_gaza.util.TrieAlgorithmUtil.Node;

public interface AttractionService {
	List<AttractionInfo> searchAttractions(Map<String, String> param);
	void registerAdminAttraction(AttractionAdminRequest attractionAdminRequest);
	String uploadAttractionImage(MultipartFile multipartFile, String realPath) throws Exception;
	AttractionDetailResponse findAttraction(int id);
	AttractionDetailResponse getAttractionDetail(int id);
	Node search(String str);
	List<AttractionAutoSearchResponse> autoComplete(String str, Node node);
	void dfs(Node node, String str);
	int getMaxSize(int size);
	List<AttractionAutoSearchResponse> attractionNameList();
	List<AttractionInfo> searchByCategory(Map<String, String> param);
	/* 여행 계획 세울 때 보이는 여행지 리스트 */
	List<AttractionPlanResponse> getAttractionPlanResponse(Map<String, String> param);
	/* 북마크 */
	int onBookmark(int attractionId, String userId) throws SQLException;
}
