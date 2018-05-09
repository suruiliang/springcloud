package com.bec.cloud.service.example.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author suruiliang
 * @version 创建时间：2018年5月8日 下午6:04:06
 * @ClassName 类名称
 * @Description 类描述
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {  
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","HEAD","OPTIONS","PUT","DELETE").allowedHeaders("Content-Type","X-Requested-With","accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers","Authorization","deviceId").allowCredentials(true);
	}
}