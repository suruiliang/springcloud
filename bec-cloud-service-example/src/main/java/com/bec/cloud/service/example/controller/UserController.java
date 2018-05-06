package com.bec.cloud.service.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bec.cloud.auth.core.support.Result;
import com.bec.cloud.auth.core.utils.SecurityUtils;

@RestController(value="_userController")
public class UserController {
	private Logger logger=LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/me")
	public Result<Authentication> me(){
		Authentication authentication=SecurityUtils.getAuthentication();
		logger.info("authentication="+authentication.getPrincipal());
		return Result.success(authentication);
	}
}
