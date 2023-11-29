package com.ssafy.trend_gaza.attraction.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ssafy.trend_gaza.attraction.service.AttractionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(
        properties = {"spring.config.location=classpath:application.properties"},
        classes = {AttractionController.class})
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
@Disabled
class AttractionControllerTest {

    @Autowired private AttractionService attractionService;

    @Autowired private MockMvc mockMvc;

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
    @DisplayName("관광지 조회 테스트 - 지역 검색")
    void testSearchAttractions1() throws Exception {
        // log.debug("관광지 조회 테스트 시작");

        mockMvc.perform(get("/attractions/search?sido=32&contentTypeId=&keyword="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // log.debug("관광지 조회 테스트 종료");

    }

    @Test
    @DisplayName("관광지 조회 테스트 - 지역, 컨텐츠 검색")
    void testSearchAttractions2() throws Exception {
        // log.debug("관광지 조회 테스트 시작");

        mockMvc.perform(get("/attractions/search?sido=32&contentTypeId=12&keyword="))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // log.debug("관광지 조회 테스트 종료");

    }

    @Test
    @DisplayName("관광지 조회 테스트 - 지역, 컨텐츠, 키워드 검색")
    void testSearchAttractions3() throws Exception {
        // log.debug("관광지 조회 테스트 시작");

        mockMvc.perform(get("/attractions/search?sido=32&contentTypeId=12&keyword=국립"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // log.debug("관광지 조회 테스트 종료");

    }

    @Test
    @DisplayName("여행 계획 세우기에서 관광지 목록 조회 테스트 ")
    void testGetAttractionPlans() throws Exception {
        // log.debug("관광지 조회 테스트 시작");

        mockMvc.perform(get("/attractions/plans?pgno=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // log.debug("관광지 조회 테스트 종료");

    }

    // customUserDetails에 사용자 정보를 넣어줘야하는데 이 부분이 잘 안 됨
    // @Test
    // @Transactional
    // @DisplayName("북마크 추가하기")
    // @WithMockUser(username="alswjd")
    // void testonBookmark() throws Exception {
    // //log.debug("관광지 조회 테스트 시작");
    //
    // Integer attractionId = 125266;
    //
    // mockMvc.perform(post("/attractions/" + attractionId + "/bookmark"))
    // .andExpect(status().isOk())
    // //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
    // .andDo(print());
    //
    // //log.debug("관광지 조회 테스트 종료");
    //
    // }

    @Test
    @DisplayName("여행 계획 세우기에 참여한 사용자들이 북마크한 관광지 조회")
    void testGetParticipantsBookmarks() throws Exception {
        // given
        int planIdx = 1;

        mockMvc.perform(get("/attractions/plan/" + planIdx + "/bookmark"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
