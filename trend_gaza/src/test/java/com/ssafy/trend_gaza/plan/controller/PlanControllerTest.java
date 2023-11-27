package com.ssafy.trend_gaza.plan.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trend_gaza.plan.dto.PlanRequest;
import com.ssafy.trend_gaza.plan.service.PlanService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(
        properties = {"spring.config.location=classpath:application.properties"},
        classes = {PlanController.class})
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
public class PlanControllerTest {

    @Autowired private PlanService planService;

    @Autowired private MockMvc mockMvc;

    @Test
    @Transactional
    @DisplayName("여행 계획 세우기")
    void testRegisterPlan() throws Exception {
        // given
        List<PlanRequest> planRequest = new ArrayList<PlanRequest>();
        planRequest.add(PlanRequest.builder().attractionId(2856807).order(1).orderDate(1).build());
        planRequest.add(PlanRequest.builder().attractionId(126479).order(1).orderDate(2).build());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(planRequest);

        // when, then
        mockMvc.perform(post("/plans/1").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("여행 계획 수정")
    void testModifyPlan() throws Exception {
        // given
        List<PlanRequest> planRequest = new ArrayList<PlanRequest>();
        planRequest.add(PlanRequest.builder().attractionId(2856807).order(1).orderDate(1).build());
        planRequest.add(PlanRequest.builder().attractionId(126479).order(1).orderDate(2).build());
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(planRequest);

        // when, then
        mockMvc.perform(put("/plans/8").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("선택한 여행 계획 조회")
    void testGetSelectAttractionPlan() throws Exception {
        // given
        int attractionPlanId = 1;

        // when, then
        mockMvc.perform(get("/plans/" + attractionPlanId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("수정 페이지 로딩용 플랜 조회")
    void testGetModifyPlan() throws Exception {
        // given
        int planIdx = 1;

        // when, then
        mockMvc.perform(get("/plans/modify/" + planIdx))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("내가 초대받은 여행 계획 조회")
    void testGetInvitedPlans() throws Exception {
        // given
        String userId = "alswjd";

        // when, then
        mockMvc.perform(get("/plans/invite/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
