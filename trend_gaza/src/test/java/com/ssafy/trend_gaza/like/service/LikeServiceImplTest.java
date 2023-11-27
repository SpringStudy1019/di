package com.ssafy.trend_gaza.like.service;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.trend_gaza.like.dto.LikeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest(
        properties = {"spring.config.location=classpath:application.properties"},
        classes = {LikeServiceImpl.class})
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"com.ssafy"})
class LikeServiceImplTest {

    @Autowired private LikeService likeService;

    @Test
    @DisplayName("좋아요")
    @Transactional
    @Disabled
    void testOnLike() {
        LikeRequest lr = new LikeRequest("user01", 6);
        try {
            int result = likeService.onLike(lr);
            assertEquals(0, result);
            //			log.debug("이미 좋아요를 누르셨습니다!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("좋아요 취소")
    @Transactional
    //	@Disabled
    void testOffLike() {
        LikeRequest lr = new LikeRequest("user01", 6);
        try {
            int result = likeService.offLike(lr);
            assertEquals(1, result);
            log.debug("좋아요를 취소했습니다!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
