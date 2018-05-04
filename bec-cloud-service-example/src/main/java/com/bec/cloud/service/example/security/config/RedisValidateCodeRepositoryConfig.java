package com.bec.cloud.service.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.bec.cloud.auth.core.validate.code.ValidateCodeRepository;
import com.bec.cloud.auth.core.validate.code.impl.RedisValidateCodeRepository;

/**
* @author suruiliang
* @version 创建时间：2018年4月12日 下午5:11:35
* @ClassName 类名称
* @Description 类描述
*/
@Configuration
public class RedisValidateCodeRepositoryConfig {
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	
	@Bean
	public ValidateCodeRepository validateCodeRepository(){
		return new RedisValidateCodeRepository(redisTemplate);
	}

}
