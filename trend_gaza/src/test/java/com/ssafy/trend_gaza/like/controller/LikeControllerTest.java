package com.ssafy.trend_gaza.like.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.ssafy.trend_gaza.like.dto.LikeRequest;
import com.ssafy.trend_gaza.like.service.LikeService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(
		properties = {
				"spring.config.location=classpath:application.properties"
		},
		classes = { LikeController.class }
)
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
class LikeControllerTest {

	@Autowired
	private LikeService likeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("좋아요 등록")
	@Transactional
	@Disabled
	void testOnLike() throws Exception {
		LikeRequest lr = new LikeRequest("user03", 2);
		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(lr);
		
		mockMvc.perform(post("/like").content(content).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful())
		.andDo(print());
		
	}

	@Test
	@DisplayName("좋아요 취소")
	@Transactional
//	@Disabled
	void testOffLike() throws Exception {
		log.debug("좋아요 취소 테스트 시작");
		LikeRequest lr = new LikeRequest("user02", 2);
		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(lr);
		
		mockMvc.perform(delete("/like").content(content).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is2xxSuccessful())
		.andDo(print());
		log.debug("좋아요 취소 테스트 종료");
	}

}
