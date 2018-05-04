package com.bec.cloud.service.example.security.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

@SuppressWarnings("deprecation")
public class ShiroHashPasswordEncoder implements PasswordEncoder{

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private final static String algorithmName = "md5";
	private final static int hashIterations = 3;

	public static String generateUserSalt() {
		return randomNumberGenerator.nextBytes().toHex();
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		return new SimpleHash(algorithmName, rawPass, ByteSource.Util.bytes(salt), hashIterations).toHex();
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return encPass.equals(new SimpleHash(algorithmName, rawPass, ByteSource.Util.bytes(salt), hashIterations).toHex());
	}


}