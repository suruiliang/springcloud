package com.bec.cloud.service.example.security.authorize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.bec.cloud.service.example.mapper.Auth2RoleMapper;
import com.bec.cloud.service.example.model.Auth2Role;

@Service
public class MyInvocationSecurityMetadataSourceService  implements
FilterInvocationSecurityMetadataSource {

	@Autowired
	private Auth2RoleMapper auth2RoleMapper;

	private List<Auth2Role> list =null;


	public void loadResourceDefine(){
		list = new ArrayList<Auth2Role>();
		List<Auth2Role> auth2Roles = auth2RoleMapper.selectAuth2Roles();
		for (Auth2Role authRole : auth2Roles) {
			handleAttribute(authRole);
		}
		list=auth2Roles;

	}

	private void handleAttribute(Auth2Role authRole) {
		String attribute=authRole.getRoles();
		if(StringUtils.isEmpty(attribute)) {
			attribute="ROLE_SUPERADMIN";
		} else if(attribute.indexOf("ROLE_NULL") >= 0 || attribute.indexOf("ROLE_PUBLIC") >= 0) {
			attribute=null;
		}
		authRole.setRoles(attribute);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if(CollectionUtils.isEmpty(list)) loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String url,method;
		List<ConfigAttribute> configAttributes=new ArrayList<>();
		for(Auth2Role authRole:list) {
			url=authRole.getUrl();
			method=authRole.getMethod();
			matcher = new AntPathRequestMatcher(url);
			if(matcher.matches(request)&&(StringUtils.equalsIgnoreCase(method, request.getMethod())||StringUtils.containsAny(method, "*","ALL","ANY"))) {
				configAttributes.add(new SecurityConfig(authRole.getRoles()));
			}
		}
		return configAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}