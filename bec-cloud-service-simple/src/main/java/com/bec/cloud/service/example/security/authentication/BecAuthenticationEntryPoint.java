package com.bec.cloud.service.example.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.support.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BecAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(Result.error(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage())));
	}

}
