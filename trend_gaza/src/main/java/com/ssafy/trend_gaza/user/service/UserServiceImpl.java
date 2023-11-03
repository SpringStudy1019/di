package com.ssafy.trend_gaza.user.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trend_gaza.user.dto.LoginRequest;
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
	public String findId(Map<String, String> map) throws Exception {
		return userMapper.findId(map);
	}

	@Override
	public String findPwd(Map<String, String> map) throws Exception {
		return userMapper.findPwd(map);
	}

	@Override
	public User view(String userId) throws Exception {
		return userMapper.view(userId);
	}

	@Override
	public void modify(Map<String, String> map) throws Exception {
		userMapper.modify(map);
	}

	@Override
	public void changePwd(Map<String, String> map) throws Exception {
		userMapper.changePwd(map);
	}

	@Override
	public void delete(String userId) throws Exception {
		userMapper.delete(userId);
		
	}
	

	

	

}
