package com.bec.cloud.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	void create(ServletWebRequest request) throws Exception;
	void validate(ServletWebRequest servletWebRequest);
}
