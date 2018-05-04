package com.bec.cloud.service.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

import com.bec.cloud.service.example.mapper.AuthUserMapper;

/**
* @author suruiliang
* @version 创建时间：2018年4月13日 下午3:28:08
* @ClassName 类名称
* @Description 类描述
*/
//@Component
public class BecSaltSource implements SaltSource {
	@Autowired
	private AuthUserMapper authUserMapper;

	@Override
	public Object getSalt(UserDetails user) {
		return authUserMapper.selectAuthUserByUsername(user.getUsername()).getUserSalt();
	}

}
