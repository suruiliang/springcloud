package com.bec.cloud.auth.core.utils;

import java.util.List;

import com.bec.cloud.auth.core.exception.EnumType;
import com.bec.cloud.auth.core.support.Result;
import com.github.pagehelper.PageInfo;

/**
* @author suruiliang
* @version 创建时间：2018年4月16日 下午1:10:42
* @ClassName 类名称
* @Description 类描述
*/
public class ResultUtil {
	@SuppressWarnings("unchecked")
	public static <T> Result<T> success(T data) {
			Result<T> result=new Result<>();
		result.setCode(200);
		result.setData(data);
		if (data instanceof List<?>) {
			result.setData((T)new PageInfo<T>((List<T>)data));
		}
		result.setMsg("成功");
		return result;
	}
	@SuppressWarnings("unchecked")
	public static <T> Result<T> success(T data,String sign,String serialNo) {
		Result<T> result=new Result<>();
		result.setCode(200);
		result.setData(data);
		if (data instanceof List<?>) {
			result.setData((T)new PageInfo<T>((List<T>)data));
		}
		result.setMsg("成功");
		result.setSign(sign);
		result.setSerialNo(serialNo);
		return result;
	}
	public static <T> Result<T> error(Integer code,String msg) {
		Result<T> result=new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	public static <T> Result<T> error(EnumType enumType) {
		Result<T> result=new Result<>();
		result.setCode(enumType.getCode());
		result.setMsg(enumType.getMsg());
		return result;
	}
	public static <T> Result<T> error(Integer code,String msg,String sign,String serialNo) {
		Result<T> result=new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		result.setSign(sign);
		result.setSerialNo(serialNo);
		return result;
	}
	public static <T> Result<T> error(EnumType enumType,String sign,String serialNo) {
		Result<T> result=new Result<>();
		result.setCode(enumType.getCode());
		result.setMsg(enumType.getMsg());
		result.setSign(sign);
		result.setSerialNo(serialNo);
		return result;
	}
}
