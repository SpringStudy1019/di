package com.ssafy.trend_gaza.attraction.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trend_gaza.attraction.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(
		properties = {
				"spring.config.location=classpath:application.properties"
		},
		classes = { AttractionController.class }
)
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
class AttractionControllerTest {
	
	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Disabled
	void testFindAttraction() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testFindAttractions() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testFindTrendAttractions() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("관광지 조회 테스트")
	void testSearchAttractions() throws Exception {
		//log.debug("관광지 조회 테스트 시작");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", "32");
		map.put("contentTypeId", "12");
		map.put("keyword", "국립");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String param = objectMapper.writeValueAsString(map);
		
		mockMvc.perform(get("/attractions/search").content(param).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andDo(print());
		
		//log.debug("관광지 조회 테스트 종료");
		
	}

}
