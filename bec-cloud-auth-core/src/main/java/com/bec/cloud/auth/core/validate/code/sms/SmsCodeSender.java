package com.bec.cloud.auth.core.validate.code.sms;

public interface SmsCodeSender {
	void send(String mobile,String code);
}
