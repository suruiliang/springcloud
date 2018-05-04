package com.bec.cloud.auth.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.bec.cloud.auth.core.properties.SecurityProperties;
import com.bec.cloud.auth.core.validate.code.ValidateCode;

//@Component("smsCodeGenerator")
public class DefaultSmsCodeGenerator implements SmsCodeGenerator {
	private SecurityProperties securityProperties;

	public final SecurityProperties getSecurityProperties() {
		return securityProperties;
	}


	public final void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}


	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code=RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}

}
