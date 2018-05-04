package com.bec.cloud.auth.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.bec.cloud.auth.core.authentication.DefaultUserDetailsService;
import com.bec.cloud.auth.core.properties.SecurityProperties;
import com.bec.cloud.auth.core.validate.code.image.DefaultImageCodeGenerator;
import com.bec.cloud.auth.core.validate.code.image.ImageCodeGenerator;
import com.bec.cloud.auth.core.validate.code.impl.RedisValidateCodeRepository;
import com.bec.cloud.auth.core.validate.code.sms.DefaultSmsCodeGenerator;
import com.bec.cloud.auth.core.validate.code.sms.DefaultSmsCodeSender;
import com.bec.cloud.auth.core.validate.code.sms.SmsCodeGenerator;
import com.bec.cloud.auth.core.validate.code.sms.SmsCodeSender;


@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class ValidateCodeBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Bean
	@ConditionalOnMissingBean(ImageCodeGenerator.class)
	public ValidateCodeGenerator imageCodeGenerator(){
		DefaultImageCodeGenerator codeGenerator=new DefaultImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeGenerator.class)
	public ValidateCodeGenerator smsCodeGenerator(){
		DefaultSmsCodeGenerator codeGenerator=new DefaultSmsCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender(){
		return new DefaultSmsCodeSender();
	}
	
	@Bean
	@ConditionalOnMissingBean(ValidateCodeRepository.class)
	public ValidateCodeRepository validateCodeRepository(){
		return new RedisValidateCodeRepository(redisTemplate);
	}
	
	@Bean
	@ConditionalOnMissingBean(AuthenticationFailureHandler.class)
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new ExceptionMappingAuthenticationFailureHandler();
	}
	
	@Bean
	@ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SavedRequestAwareAuthenticationSuccessHandler();
	}
	
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService userDetailsService() {
		return new DefaultUserDetailsService();
	}
	
	/*@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}*/

}
