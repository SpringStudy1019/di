package com.ssafy.trend_gaza.user.repository;

import java.sql.SQLException;
import java.util.Map;

import org.mapstruct.Mapper;

import com.ssafy.trend_gaza.user.dto.ChangePwdRequest;
import com.ssafy.trend_gaza.user.dto.FindIdRequest;
import com.ssafy.trend_gaza.user.dto.FindPwdRequest;
import com.ssafy.trend_gaza.user.dto.LoginRequest;
import com.ssafy.trend_gaza.user.dto.ModifyRequest;
import com.ssafy.trend_gaza.user.dto.RegisterRequest;
import com.ssafy.trend_gaza.user.entity.User;

@Mapper
public interface UserMapper {

	/* 회원가입 */	
	void register(RegisterRequest registerRequest) throws SQLException;
	
	/* 회원가입 시 아이디 중복 체크 */
	int idCheck(String userId) throws SQLException;
	
	/* 로그인 */
	User login(LoginRequest loginRequest) throws SQLException;
	User userInfo(String userId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	/* 아이디 찾기: 이름과 핸드폰번호로 아이디 찾기 */
	String findId(FindIdRequest findIdRequest) throws SQLException;
	
	/* 비밀번호 찾기: 아이디, 이름, 핸드폰 번호 입력하면, 임시 비밀번호 발급  */
	String findPwd(FindPwdRequest findPwdRequest) throws SQLException;
	
	/* 내 정보 조회: 세션에 저장된 사용자 아이디를 받아서 entity 리턴 */
	User view(String userId) throws SQLException;
	
	/* 내 정보 수정 */
	void modify(ModifyRequest modifyRequest) throws SQLException;
	
	/* 비밀번호 변경: 현재 비밀번호와 새로운 비밀번호 받기  */
	int changePwd(ChangePwdRequest changePwdRequest) throws SQLException;
	
	/* 회원탈퇴 */
	void delete(String userId) throws SQLException;
	
	void changePwdToTempPwd(Map<String, String> map) throws SQLException;
	
}
