package com.bec.cloud.service.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.model.AuthUser;

//@Component
public class BecUserDetailsService implements UserDetailsService {
	@Autowired
	private AuthUserMapper authUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthUser authUser=authUserMapper.selectAuthUserByUsername(username);
		if (authUser==null) {
			throw new UsernameNotFoundException("username not found");
		}
		return new User(username, authUser.getUserPasswd(),true, true, true, true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}

}
