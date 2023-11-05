package com.ssafy.trend_gaza.review.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import com.ssafy.trend_gaza.review.entity.Review;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" }, classes = {
		ReviewServiceImpl.class })
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "com.ssafy" })
@Transactional
class ReviewServiceImplTest {
	
	@Autowired
	private ReviewService reviewService;

	@Test
	@Disabled
	void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("리뷰 검색어 조회 테스트")
	void testList() {
		Map<String, String> param = new HashMap<>();
		param.put("word", "oh");
		try {
			List<Review> list = reviewService.list(param);
			assertEquals(1, list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Disabled
	void testModify() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDelete() {
		fail("Not yet implemented");
	}

}
