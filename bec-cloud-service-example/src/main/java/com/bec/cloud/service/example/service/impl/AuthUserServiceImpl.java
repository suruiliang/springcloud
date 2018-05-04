package com.bec.cloud.service.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.model.AuthUser;
import com.bec.cloud.service.example.service.AuthUserService;

@Service(value = "authUserService")
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
    private AuthUserMapper authUserMapper;

	@Override
	public List<AuthUser> selectAuthUser(AuthUser authUser) {
		return authUserMapper.selectAuthUser(authUser);
	}

    

}
