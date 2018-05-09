package com.bec.cloud.service.example.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bec.cloud.auth.core.exception.BecException;
import com.bec.cloud.auth.core.exception.BecExceptionEnum;

/**
 * @author suruiliang
 * @version 创建时间：2018年5月9日 下午1:30:23
 * @ClassName 类名称
 * @Description 类描述
 */
public class CheckParamUtil {
	private final static String defaultMsg="不能为空";
	public static void checkParam(Object o,String ... params){
		Map<String, String> map=new HashMap<String, String>();
		try {
			final Class<?> objClass=o.getClass();  
			for (int i=0,len=params.length;i<len;i++) {
				Field field=objClass.getDeclaredField(params[i]);
				field.setAccessible(true);
				if (String.class.isAssignableFrom(field.getType())) {
					if (field.get(o)==null||"".equals(field.get(o).toString())) {
						map.put(field.getName(), defaultMsg);
					}
				}
				if (Number.class.isAssignableFrom(field.getType())) {
					if (field.get(o)==null) {
						map.put(field.getName(), defaultMsg);
					}
				}
				if (Boolean.class.isAssignableFrom(field.getType())) {
					if (field.get(o)==null) {
						map.put(field.getName(), defaultMsg);
					}
				}
				if (Date.class.isAssignableFrom(field.getType())) {
					if (field.get(o)==null) {
						map.put(field.getName(), defaultMsg);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BecException(BecExceptionEnum.UNKNOWN_EXCEPTION);
		}
		if (!map.isEmpty()) {
			throw new BecException(500,getMsg(map));
		}
	}
	private static String getMsg(Map<String, String> map) {
		StringBuffer sb=new StringBuffer();
		map.entrySet().forEach(m->{
			sb.append("字段"+m.getKey()+m.getValue()+";");
		});
		String msg=sb.toString();
		return msg;
	}  
}
