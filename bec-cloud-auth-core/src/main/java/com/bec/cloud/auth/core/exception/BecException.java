package com.bec.cloud.auth.core.exception;

/**
 * 
 * @date 2018年4月16日下午3:06:10
 * @author suruiliang
 *
 */
public class BecException extends RuntimeException {
	private static final long serialVersionUID = -1986153897139114805L;
	private Integer code;
	public BecException() {
	}
	public BecException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}
	public BecException(EnumType exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.code = exceptionEnum.getCode();
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
