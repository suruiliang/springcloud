package com.bec.cloud.service.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bec.cloud.auth.core.exception.BecException;
import com.bec.cloud.auth.core.support.Result;


@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result<?> handleException(Exception ex) {
		ex.printStackTrace();
		if (ex instanceof BecException) {
			return Result.error(((BecException)ex).getCode(),ex.getMessage());
		}
		return Result.error(500,ex.getMessage());
	}
}
