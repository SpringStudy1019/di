package com.ssafy.trend_gaza.attraction.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trend_gaza.attraction.dto.AttractionAdminRequest;
import com.ssafy.trend_gaza.attraction.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" }, classes = {
		AttractionAdminController.class })
@Slf4j
@ComponentScan(basePackages = { "com.ssafy" })
@Disabled
class AttractionAdminControllerTest {

	@Autowired
	private AttractionService attractionService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("관광지 등록")
	@Transactional
	void testCreateAttraction() throws Exception {

		// given
		AttractionAdminRequest attractionAdminRequest = AttractionAdminRequest.builder().attractionId(2856807)
				.contentTypeId(12).title("관광지명").address("주소").tel("연락처").defaultImg("이미지 경로").latitude(1.0)
				.longitude(1.0).build();

		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(attractionAdminRequest);

		// when
		mockMvc.perform(post("/admin/attractions").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				// .andExpect(header().string(LOCATION, "/admin/attractions"))
				.andDo(print());
	}
}
