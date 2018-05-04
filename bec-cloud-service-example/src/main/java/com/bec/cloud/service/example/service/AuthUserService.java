package com.bec.cloud.service.example.service;

import java.util.List;

import com.bec.cloud.service.example.model.AuthUser;

public interface AuthUserService {
	public List<AuthUser> selectAuthUser(AuthUser authUser);
}
