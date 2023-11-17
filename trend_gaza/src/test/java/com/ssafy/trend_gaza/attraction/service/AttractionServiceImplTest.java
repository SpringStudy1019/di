package com.ssafy.trend_gaza.attraction.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.dto.AttractionPlanResponse;
import com.ssafy.trend_gaza.attraction.entity.AttractionInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" }, classes = {
		AttractionServiceImpl.class })
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "com.ssafy" })
class AttractionServiceImplTest {

	@Autowired
	private AttractionService attractionService;

	@Test
	@DisplayName("관광지 조회 테스트 - 지역  검색")
	void testSearchAttractions1() {
		// log.debug("관광지 조회 테스트 시작");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", "32");
		map.put("contentTypeId", "");
		map.put("keyword", "");
		
		List<AttractionInfo> attractionInfo = attractionService.searchAttractions(map);
		assertEquals(4411, attractionInfo.size());
		
		// log.debug("관광지 조회 테스트 종료");
	}
	
	@Test
	@DisplayName("관광지 조회 테스트 - 컨텐츠  검색")
	void testSearchAttractions2() {
		// log.debug("관광지 조회 테스트 시작");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", "32");
		map.put("contentTypeId", "12");
		map.put("keyword", "");
		
		List<AttractionInfo> attractionInfo = attractionService.searchAttractions(map);
		assertEquals(1356, attractionInfo.size());
		
		// log.debug("관광지 조회 테스트 종료");
	}
	
	@Test
	@DisplayName("관광지 조회 테스트 - 지역, 컨텐츠, 키워드  검색")
	void testSearchAttractions3() {
		// log.debug("관광지 조회 테스트 시작");

		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", "32");
		map.put("contentTypeId", "12");
		map.put("keyword", "국립");

		List<AttractionInfo> attractionInfo = attractionService.searchAttractions(map);
		assertEquals(15, attractionInfo.size());

		// log.debug("관광지 조회 테스트 종료");
	}

	@Test
	@DisplayName("[관리자 권한] 관광지 등록 테스트")
	@Transactional
	void testRegisterAdminAttraction() {

		// given
		AttractionAdminRequest attractionAdminRequest = AttractionAdminRequest.builder().attractionId(2856807)
				.contentTypeId(12).title("관광지명").address("주소").tel("연락처").defaultImg("이미지 경로").latitude(1.0)
				.longitude(1.0).build();
		
		// when
		attractionService.registerAdminAttraction(attractionAdminRequest);
	}
	
	@Test
	@DisplayName("여행 계획 세우기에서 관광지 목록 조회 테스트")
	void testGetAttractionPlanResponse() {

		// given
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("pgno", "2");
		
		// when
		List<AttractionPlanResponse> result = attractionService.getAttractionPlanResponse(map);
		
		// then
		assertEquals(20, result.size());
		
	}

}
