package com.bec.cloud.auth.core.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DefaultUserDetailsService implements UserDetailsService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username="+username);
		if (!"admin".equals(username)) {
			throw new UsernameNotFoundException(username+" not found!");
		}
		return new User("admin", passwordEncoder.encode("123456"),true, true, true, true,AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
	}

}