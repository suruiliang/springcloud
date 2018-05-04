package com.bec.cloud.service.example.security.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bec.cloud.service.example.mapper.AuthRoleMapper;
import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.model.AuthRole;
import com.bec.cloud.service.example.model.AuthUser;

//@Component
public class BecUserDetailsService implements UserDetailsService {
	@Autowired
	private AuthUserMapper authUserMapper;
	@Autowired
	private AuthRoleMapper authRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthUser authUser=authUserMapper.selectAuthUserByUsername(username);
		if (authUser==null) {
			throw new UsernameNotFoundException("username not found");
		}
		
		List<AuthRole> authRoles=authRoleMapper.selectAuthRole(username);
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		if (authRoles!=null&&authRoles.size()>0) {
			for (AuthRole a : authRoles) {
				authorities.add(new SimpleGrantedAuthority(a.getRoleCode()));
			}	
		}
		
		return new User(authUser.getUserName(), authUser.getUserPasswd(),true, true, true, true,authorities);
	}

}
