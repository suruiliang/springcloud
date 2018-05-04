package com.bec.cloud.auth.core.validate.code.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import com.bec.cloud.auth.core.validate.code.ValidateCode;
import com.bec.cloud.auth.core.validate.code.ValidateCodeException;
import com.bec.cloud.auth.core.validate.code.ValidateCodeRepository;
import com.bec.cloud.auth.core.validate.code.ValidateCodeType;

//@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {
	private RedisTemplate<Object, Object> redisTemplate;

	public RedisValidateCodeRepository(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate=redisTemplate;
	}

	public RedisTemplate<Object, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
		redisTemplate.opsForValue().set(buildKey(request,validateCodeType), code, 30, TimeUnit.SECONDS);
	}

	private String buildKey(ServletWebRequest request, ValidateCodeType type) {
		String deviceId=request.getHeader("deviceId");
		if (StringUtils.isBlank(deviceId)) {
			throw new ValidateCodeException("请在请求头中携带deviceId参数");
		}
		return "code:"+type.toString().toUpperCase()+":"+deviceId;
	}

	@Override
	public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
		Object value=redisTemplate.opsForValue().get(buildKey(request,validateCodeType));
		if (value==null) {
			return null;
		}
		return (ValidateCode) value;
	}

	@Override
	public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
		redisTemplate.delete(buildKey(request, validateCodeType));
	}

}
