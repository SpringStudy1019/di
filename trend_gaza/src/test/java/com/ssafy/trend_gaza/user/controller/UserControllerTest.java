package com.ssafy.trend_gaza.user.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.ssafy.trend_gaza.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@AutoConfigureMockMvc
@SpringBootTest(
	properties = {
			"spring.config.location=classpath:application.properties"
	},
	classes = { UserController.class }
)
@Slf4j
@ComponentScan(basePackages = {"com.ssafy"})
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	private UserService userService; 
	
	
	@Test
	@Disabled
	void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testIdCheck() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
//	@DisplayName("### 로그인 테스트 ###")
	void testLogin() throws Exception {
		log.debug("### 로그인 테스트 시작 ###");

		log.debug("### 로그인 테스트 종료 ###");
	}

	@Test
	@Disabled
	void testFindId() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testFindPwd() {
		fail("Not yet implemented");
	}

	@Test
//	@Disabled
	@DisplayName("### 회원 정보 얻기 테스트 ###")
	void testView() throws Exception {
		log.debug("### 회원 정보 얻기 테스트 시작 ###");
		
		String userId = "user01";
		
		mockMvc.perform(get("/user/view/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andDo(print());
	}

	@Test
	@Disabled
	void testModify() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testChangePwd() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testUserDelete() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testLogout() {
		fail("Not yet implemented");
	}

}
