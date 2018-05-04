package com.bec.cloud.service.example.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.exception.BecExceptionEnum;
import com.bec.cloud.auth.core.properties.LoginType;
import com.bec.cloud.auth.core.properties.SecurityProperties;
import com.bec.cloud.auth.core.utils.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(value="becAuthenticationFailureHandler")
public class BecAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");
		if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			//response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(ResultUtil.error(BecExceptionEnum.AUTHENTICATIONFAILURE_EXCEPTION)));
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
		

	}

}
