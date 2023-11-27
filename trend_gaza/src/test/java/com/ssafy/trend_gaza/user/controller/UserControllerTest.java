package com.ssafy.trend_gaza.user.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trend_gaza.user.dto.ChangePwdRequest;
import com.ssafy.trend_gaza.user.dto.FindIdRequest;
import com.ssafy.trend_gaza.user.dto.FindPwdRequest;
import com.ssafy.trend_gaza.user.dto.LoginRequest;
import com.ssafy.trend_gaza.user.dto.ModifyRequest;
import com.ssafy.trend_gaza.user.dto.RegisterRequest;
import com.ssafy.trend_gaza.user.service.UserService;
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
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(
        properties = {"spring.config.location=classpath:application.properties"},
        classes = {UserController.class})
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
@Transactional
class UserControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private UserService userService;

    @Test
    @Disabled
    @DisplayName("### 회원가입 테스트 ###")
    void testRegister() throws Exception {
        log.debug("### 회원가입 테스트 시작 ###");
        RegisterRequest registerRequest =
                new RegisterRequest(
                        "user04", "홍싸피", "1234", "01055553333", "user04", "naver.com", "MALE");
        // 이 Dto를 json으로 바꿔서 넘긴 후에 그 json을 다시 string으로 바꿔서 넣어야 한다.
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(
                        post("/user/register")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                //        .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful())
                //        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        log.debug("### 회원가입 테스트 종료 ###");
    }

    @Test
    @Disabled
    @DisplayName("### 아이디 중복 테스트 ###")
    void testIdCheck() throws Exception {
        log.debug("### 아이디 중복 테스트 시작 ###");

        String userId = "user01";
        String userId2 = "user05";

        mockMvc.perform(get("/user/idCheck/{userId}", userId2))
                .andExpect(status().is2xxSuccessful())
                //            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        log.debug("### 아이디 중복 테스트 종료 ###");
    }

    @Test
    @Disabled
    @DisplayName("### 로그인 테스트 ###")
    void testLogin() throws Exception {
        log.debug("### 로그인 테스트 시작 ###");

        LoginRequest loginRequest = new LoginRequest("user03", "1234");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(
                        post("/user/login")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        log.debug("### 로그인 테스트 종료 ###");
    }

    @Test
    @Disabled
    @DisplayName("### 아이디 찾기 테스트 ###")
    void testFindId() throws Exception {

        FindIdRequest findIdRequest = new FindIdRequest("홍싸피", "01055553333");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(findIdRequest);

        mockMvc.perform(
                        post("/user/findId")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    @Disabled
    @DisplayName("### 비밀번호 찾기 테스트 ###")
    void testFindPwd() throws Exception {

        FindPwdRequest findPwdRequest = new FindPwdRequest("user04", "홍싸피", "01055553333");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(findPwdRequest);

        mockMvc.perform(
                        post("/user/findPwd")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print());
    }

    @Test
    @Disabled
    @DisplayName("### 회원 정보 얻기 테스트 ###")
    void testView() throws Exception {
        log.debug("### 회원 정보 얻기 테스트 시작 ###");

        String userId = "user01";
        String userName = "김싸피";

        mockMvc.perform(get("/user/view/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(userName))) // 통과 기준 json 값 가져올 때 $.
                .andDo(print());

        log.debug("### 회원 정보 얻기 테스트 종료 ###");
    }

    @Test
    @Disabled
    @DisplayName("### 회원 정보 수정 테스트 ###")
    void testModify() throws Exception {
        ModifyRequest modifyRequest =
                new ModifyRequest("user04", "홍싸피", "01077773333", "user04", "ssafy.com", "MALE");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(modifyRequest);

        mockMvc.perform(
                        put("/user/modify")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Disabled
    @DisplayName("### 비밀번호 변경 테스트 ###")
    void testChangePwd() throws Exception {
        ChangePwdRequest changePwdRequest = new ChangePwdRequest("user04", "1234", "1111");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(changePwdRequest);

        mockMvc.perform(
                        put("/user/changePwd")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    //	@Disabled
    @DisplayName("### 사용자 삭제 테스트 ###")
    void testUserDelete() throws Exception {
        String userId = "user04";

        mockMvc.perform(delete("/user").param("userId", userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Disabled
    void testLogout() {
        fail("Not yet implemented");
    }
}
