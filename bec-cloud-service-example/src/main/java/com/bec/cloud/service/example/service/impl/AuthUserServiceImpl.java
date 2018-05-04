package com.bec.cloud.service.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.service.AuthUserService;

@Service(value = "authUserService")
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
    private AuthUserMapper authUserMapper;


}
