package com.bec.cloud.service.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bec.cloud.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bec.cloud.auth.core.authorize.AuthorizeConfigManager;
import com.bec.cloud.auth.core.properties.SecurityConstants;
import com.bec.cloud.auth.core.validate.code.ValidateCodeSecurityConfig;

@Configuration
@EnableResourceServer
public class BecResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	@Autowired
	private AuthenticationSuccessHandler becAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler becAuthenticationFailureHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
		.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
		.successHandler(becAuthenticationSuccessHandler)
		.failureHandler(becAuthenticationFailureHandler);

		http
		.apply(validateCodeSecurityConfig)
		.and()
		.apply(smsCodeAuthenticationSecurityConfig)
		.and()
		.cors()//跨域
		.and()
		.csrf()
		.disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}

}
