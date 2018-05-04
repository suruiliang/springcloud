package com.bec.cloud.service.example.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.utils.SecurityUtils;
import com.bec.cloud.service.example.mapper.AuthOrganizationMapper;
import com.bec.cloud.service.example.mapper.AuthRoleMapper;
import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.model.AuthOrganization;
import com.bec.cloud.service.example.model.AuthRole;
import com.bec.cloud.service.example.model.AuthUser;
import com.bec.cloud.service.example.model.UserInfo;

@Component
public class UserInfoUtil {
	@Autowired
	private AuthUserMapper authUserMapper;
	@Autowired
	private AuthRoleMapper authRoleMapper;
	@Autowired
	private AuthOrganizationMapper authOrganizationMapper;
	//TODO customer
	
	public UserInfo userInfo() {
		UserInfo userInfo=new UserInfo();
		String userName=SecurityUtils.getCurrentUserUsername();
		userInfo.setUserName(userName);
		AuthUser authUser=authUserMapper.selectAuthUserByUsername(userName);
		if (authUser==null) {
			return userInfo;
		}
		try {
			BeanUtils.copyProperties(userInfo, authUser);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		List<AuthRole> authRoles=authRoleMapper.selectAuthRole(userName);
		if (authRoles!=null&&authRoles.size()>0) {
			userInfo.setAuthRoles(authRoles);
		}
		List<AuthOrganization> authOrganizations=authOrganizationMapper.selectAuthOrganization(userName);
		if (authOrganizations!=null&&authOrganizations.size()>0) {
			userInfo.setAuthOrganizations(authOrganizations);
		}
		//TODO customer
		return userInfo;
	}
}
