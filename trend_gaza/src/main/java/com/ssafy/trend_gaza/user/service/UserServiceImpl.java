package com.ssafy.trend_gaza.user.service;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.user.dto.ChangePwdRequest;
import com.ssafy.trend_gaza.user.dto.FindIdRequest;
import com.ssafy.trend_gaza.user.dto.FindPwdRequest;
import com.ssafy.trend_gaza.user.dto.LoginRequest;
import com.ssafy.trend_gaza.user.dto.ModifyRequest;
import com.ssafy.trend_gaza.user.dto.RegisterRequest;
import com.ssafy.trend_gaza.user.entity.User;
import com.ssafy.trend_gaza.user.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public void register(RegisterRequest registerRequest) throws Exception {
		userMapper.register(registerRequest);;
		
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public User login(LoginRequest loginRequest) throws Exception {
		return userMapper.login(loginRequest);
	}

	@Override
	public String findId(FindIdRequest findIdRequest) throws Exception {
		return userMapper.findId(findIdRequest);
	}

	@Override
	public String findPwd(FindPwdRequest findPwdRequest) throws Exception {
		return userMapper.findPwd(findPwdRequest);
	}

	@Override
	public User view(String userId) throws Exception {
		return userMapper.view(userId);
	}

	@Override
	public void modify(ModifyRequest modifyRequest) throws Exception {
		userMapper.modify(modifyRequest);
	}

	@Override
	public void changePwd(ChangePwdRequest changePwdRequest) throws Exception {
		userMapper.changePwd(changePwdRequest);
	}

	@Override
	public void delete(String userId) throws Exception {
		userMapper.delete(userId);
		
	}
	

	

	

}
