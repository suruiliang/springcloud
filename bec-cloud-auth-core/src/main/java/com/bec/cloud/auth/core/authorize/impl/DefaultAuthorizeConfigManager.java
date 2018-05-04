package com.bec.cloud.auth.core.authorize.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.authorize.AuthorizeConfigManager;
import com.bec.cloud.auth.core.authorize.AuthorizeConfigProvider;


@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {
	@Autowired
	private List<AuthorizeConfigProvider> authorizeConfigProviders;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		for (AuthorizeConfigProvider authorizeConfigProvider:authorizeConfigProviders) {
			authorizeConfigProvider.config(config);
		}
		config.anyRequest().authenticated();
	}

}
