package com.bec.cloud.service.example.security.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bec.cloud.auth.core.support.Result;

@RestController
@RequestMapping(value="/authentication")
public class BecAuthenticationLogoutController {
	@Autowired
	private TokenStore tokenStore;

	@RequestMapping(value="/logout",method={RequestMethod.POST,RequestMethod.DELETE})
	public Result<?> logout(HttpServletRequest request) {
		String access_token=(String) request.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE);
		((RedisTokenStore)tokenStore).removeAccessToken(access_token);
		return Result.success(access_token);
	}

}
