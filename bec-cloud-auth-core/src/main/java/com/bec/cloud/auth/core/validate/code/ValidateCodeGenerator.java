package com.bec.cloud.auth.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
	ValidateCode generate(ServletWebRequest request);
}
