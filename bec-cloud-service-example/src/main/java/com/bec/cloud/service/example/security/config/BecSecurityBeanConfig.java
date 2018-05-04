package com.bec.cloud.service.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import com.bec.cloud.service.example.security.service.BecClientDetailsService;
import com.bec.cloud.service.example.security.service.BecUserDetailsService;


/**
* @author suruiliang
* @version 创建时间：2018年4月12日 下午1:55:41
* @ClassName 类名称
* @Description 类描述
*/
@SuppressWarnings("deprecation")
@Configuration
public class BecSecurityBeanConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public BecAuthorizationServerConfig becAuthorizationServerConfig() {
		return new BecAuthorizationServerConfig();
	}
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new BecUserDetailsService();
	}
	
	@Bean
	public ClientDetailsService clientDetailsService(){
		return new BecClientDetailsService();
	}

}
