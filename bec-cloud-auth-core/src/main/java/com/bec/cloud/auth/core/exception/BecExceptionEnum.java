package com.bec.cloud.auth.core.exception;
/**
* @author suruiliang
* @version 创建时间：2018年4月11日 下午5:16:00
* @ClassName 类名称
* @Description 类描述
*/
public enum BecExceptionEnum implements EnumType {
	AUTHENTICATIONFAILURE_EXCEPTION(1001,"登录失败"),
	UNKNOWN_EXCEPTION(1002,"未知错误"),
	PASSWORD_EXCEPTION(1003,"密码错误"),
	NORIGHT_EXCEPTION(1004,"无权限");
	private Integer code;
	private String msg;
	
	private BecExceptionEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	@Override
	public Integer getCode() {
		return code;
	}
	@Override
	public String getMsg() {
		return msg;
	}
}
