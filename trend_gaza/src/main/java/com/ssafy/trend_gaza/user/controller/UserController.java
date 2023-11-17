package com.ssafy.trend_gaza.user.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trend_gaza.user.dto.ChangePwdRequest;
import com.ssafy.trend_gaza.user.dto.FindIdRequest;
import com.ssafy.trend_gaza.user.dto.FindPwdRequest;
import com.ssafy.trend_gaza.user.dto.LoginRequest;
import com.ssafy.trend_gaza.user.dto.ModifyRequest;
import com.ssafy.trend_gaza.user.dto.RegisterRequest;
import com.ssafy.trend_gaza.user.entity.User;
import com.ssafy.trend_gaza.user.service.UserService;
import com.ssafy.trend_gaza.util.AuthenticationUtil;
import com.ssafy.trend_gaza.util.JWTUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	private JWTUtil jwtUtil; 
	
	public UserController(UserService userService, JWTUtil jwtUtil) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		logger.debug("register Dto : {}", registerRequest);
		try {
			userService.register(registerRequest);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping(value = "/idCheck/{userId}")
	public ResponseEntity<?> idCheck(@PathVariable String userId) {
		try {
			int count = userService.idCheck(userId);
			if (count == 1) {
				logger.debug("사용 불가능한 아이디입니다.");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} else {
				logger.debug("사용 가능한 아이디입니다.");
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	} 
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		logger.debug("login : {}", loginRequest);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			User user = userService.login(loginRequest);
			if(user != null) {
				String accessToken = jwtUtil.createAccessToken(user.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(user.getUserId());
				
//				발급받은 refresh token을 DB에 저장.
				userService.saveRefreshToken(user.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			}
			else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;				
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable String userId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			try {
//				로그인 사용자 정보.
				User user = userService.userInfo(userId);
				resultMap.put("userInfo", user);
				status = HttpStatus.OK;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(user.getUserId());
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping(value = "/findId", produces = "text/plain")
	public ResponseEntity<?> findId(@RequestBody FindIdRequest findIdRequest) {
		try {
			String id = userService.findId(findIdRequest);
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}	
	}
	
	@PostMapping("/send-email")
	public ResponseEntity<?> sendEmail(@RequestParam String userId) throws Exception {
		//User loginUser = (User)httpSession.getAttribute("userInfo");
		//userService.sendEmail(loginUser.getUserId());
		userService.sendEmail(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/findPwd", produces = "text/plain")
	public ResponseEntity<?> findPwd(@RequestBody FindPwdRequest findPwdRequest) {
		try {
			String password = userService.findPwd(findPwdRequest);
			if (password != "") { // 매칭되는 비밀번호가 있으면
				UUID uuid = UUID.randomUUID();
				logger.debug("임시비밀번호: "+uuid);
				String tempPwd = uuid.toString().substring(0, 8); // 비밀번호 8자리
				logger.debug("임시비밀번호: "+tempPwd);
				// 사용자에게 임시 비밀번호 메일 발송 
				
				// 사용자의 비밀번호를 암호화하여 DB 변경. 
//				ChangePwdRequest changePwdRequest = new ChangePwdRequest();
//				User userinfo = (User) session.getAttribute("userinfo");
//				changePwdRequest.setUserId(userinfo.getUserId());
//				changePwdRequest.setPassword(password);
//				changePwdRequest.setNewPassword(tempPwd);
//				userService.changePwd(changePwdRequest);
				
				return new ResponseEntity<String>(tempPwd, HttpStatus.OK);
				
			} else {				
				logger.debug("비밀번호를 찾을 수 없습니다.");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@GetMapping(value = "/view/{userId}")
	public ResponseEntity<?> view(@PathVariable String userId) {
		try {
			User user = userService.view(userId);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@PutMapping(value = "/modify")
	public ResponseEntity<?> modify(@RequestBody ModifyRequest modifyRequest, HttpSession session) {
		try {
			userService.modify(modifyRequest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@PutMapping(value = "/changePwd")
	public ResponseEntity<?> changePwd(@RequestBody ChangePwdRequest changePwdRequest, HttpSession session) {
		try {
			int result = userService.changePwd(changePwdRequest);
			if (result == 1) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> userDelete(String userId) {
		logger.debug("userDelete userid : {}", userId);
		try {
			userService.delete(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable String userId) throws Exception {
		userService.logout(userId);
		return ResponseEntity.ok(URI.create("/"));	
	}
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
