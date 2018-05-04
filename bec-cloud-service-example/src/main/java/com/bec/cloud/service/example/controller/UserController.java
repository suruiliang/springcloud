package com.bec.cloud.service.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bec.cloud.auth.core.support.Result;
import com.bec.cloud.auth.core.utils.ResultUtil;
import com.bec.cloud.auth.core.utils.SecurityUtils;
import com.bec.cloud.service.example.model.AuthUser;
import com.bec.cloud.service.example.service.AuthUserService;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private AuthUserService authUserService;

	@GetMapping(value = "/me")
	public Result<Authentication> me(){
		Authentication authentication=SecurityUtils.getAuthentication();
		logger.info("authentication="+authentication.getPrincipal());
		return ResultUtil.success(authentication);
	}
	@GetMapping(value = "/list")
	public Result<?> list(AuthUser authUser,Integer pageNum,Integer pageSize){
		if(pageNum!=null&&pageSize!=null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return ResultUtil.success(authUserService.selectAuthUser(authUser));
	}
	
}
