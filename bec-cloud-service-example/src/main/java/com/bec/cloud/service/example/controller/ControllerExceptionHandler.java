package com.bec.cloud.service.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bec.cloud.auth.core.exception.BecException;
import com.bec.cloud.auth.core.support.Result;
import com.bec.cloud.auth.core.utils.ResultUtil;


@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(BecException.class)
	@ResponseBody
	public Result<?> handleBecException(BecException ex) {
		return ResultUtil.error(ex.getCode(),ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<?> handleException(Exception ex) {
		return ResultUtil.error(500,ex.getMessage());
	}
}
