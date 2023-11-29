package com.ssafy.trend_gaza.review.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trend_gaza.review.dto.ReviewResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" }, classes = {
		ReviewServiceImpl.class })
@AutoConfigureMockMvc
@ComponentScan(basePackages = { "com.ssafy" })
@Transactional
@Disabled
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
		Map<String, String> map = new HashMap<>();
		map.put("word", "oh");
		map.put("pgno", "1");
		map.put("spp", "20");
		map.put("key", "title");

		try {
			ReviewResponse reviewResponse = reviewService.list(map);
			log.debug("assertNotNull(reviewResponse)");
			assertNotNull(reviewResponse);
			log.debug(" assertNotNull(reviewResponse.getReviews())");
			assertNotNull(reviewResponse.getReviews());
			log.debug(" assertEquals(1, reviewResponse.getReviews().size())");
			assertEquals(1, reviewResponse.getReviews().size());
			log.debug("assertEquals(1, reviewResponse.getCurrentPage())");
			assertEquals(1, reviewResponse.getCurrentPage());
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
