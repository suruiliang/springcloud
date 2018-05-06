package com.bec.cloud.service.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bec.cloud.auth.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bec.cloud.auth.core.authorize.AuthorizeConfigManager;
import com.bec.cloud.auth.core.properties.SecurityConstants;
import com.bec.cloud.auth.core.validate.code.ValidateCodeSecurityConfig;
import com.bec.cloud.service.example.security.authentication.BecAuthenticationEntryPoint;
import com.bec.cloud.service.example.security.authorize.BecAccessDeniedHandler;
import com.bec.cloud.service.example.security.authorize.MyFilterSecurityInterceptor;

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
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	@Autowired
	private BecAccessDeniedHandler becAccessDeniedHandler;
	@Autowired
	private BecAuthenticationEntryPoint becAuthenticationEntryPoint;

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
		.disable()
		.exceptionHandling()
		.accessDeniedHandler(becAccessDeniedHandler)
		.authenticationEntryPoint(becAuthenticationEntryPoint);
		//可扩展权限配置
		authorizeConfigManager.config(http.authorizeRequests());
		//自定义权限过滤器
		http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(becAuthenticationEntryPoint);
		super.configure(resources);
	}

}
