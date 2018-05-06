package com.bec.cloud.service.example.security.authorize;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.support.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BecAccessDeniedHandler implements AccessDeniedHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (!response.isCommitted()) {

			response.getWriter().write(objectMapper.writeValueAsString(Result.error(HttpServletResponse.SC_FORBIDDEN,
					accessDeniedException.getMessage())));
		}
	}

}
