package com.ssafy.trend_gaza.user.service;

import com.ssafy.trend_gaza.user.dto.ChangePwdRequest;
import com.ssafy.trend_gaza.user.dto.FindIdRequest;
import com.ssafy.trend_gaza.user.dto.FindPwdRequest;
import com.ssafy.trend_gaza.user.dto.LoginRequest;
import com.ssafy.trend_gaza.user.dto.ModifyRequest;
import com.ssafy.trend_gaza.user.dto.RegisterRequest;
import com.ssafy.trend_gaza.user.entity.User;

public interface UserService {

	/* 회원가입 */	
	void register(RegisterRequest registerRequest) throws Exception;
	
	/* 회원가입 시 아이디 중복 체크 */
	int idCheck(String userId) throws Exception;
	
	/* 로그인, 로그아웃은 컨트롤러에서 */
	User login(LoginRequest loginRequest) throws Exception;
	
	/* 아이디 찾기 */
	String findId(FindIdRequest findIdRequest) throws Exception;
	
	/* 비밀번호 찾기: 아이디, 이름, 핸드폰 번호 입력하면, 임시 비밀번호 발급  */
	String findPwd(FindPwdRequest findPwdRequest) throws Exception;
	
	/* 내 정보 조회: 세션에 저장된 사용자 아이디를 받아서 entity 리턴 */
	User view(String userId) throws Exception;
	
	/* 내 정보 수정 */
	void modify(ModifyRequest modifyRequest) throws Exception;
	
	/* 비밀번호 변경: 현재 비밀번호와 새로운 비밀번호 받기  */
	void changePwd(ChangePwdRequest changePwdRequest) throws Exception;
	
	/* 회원탈퇴: 비밀번호 입력 받아야 함. */
	void delete(String userId) throws Exception;
	
	void sendEmail(String email) throws Exception;

}
