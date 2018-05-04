package com.bec.cloud.service.example.security.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import com.bec.cloud.service.example.mapper.AuthClientDetailsMapper;
import com.bec.cloud.service.example.model.AuthClientDetails;


/**
* @author suruiliang
* @version 创建时间：2018年4月11日 下午5:45:00
* @ClassName 类名称
* @Description 类描述
*/
public class BecClientDetailsService implements  ClientDetailsService{
	@Autowired
	private AuthClientDetailsMapper authClientDetailsMapper;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		AuthClientDetails authClientDetails=authClientDetailsMapper.selectByPrimaryKey(clientId);
		if (authClientDetails==null) {
			throw new ClientRegistrationException("client not exists");
		}
		return getClient(authClientDetails);
	}

	private BaseClientDetails getClient(AuthClientDetails authClientDetails) {
		BaseClientDetails clients=new BaseClientDetails();
		clients.setClientId(authClientDetails.getClientId());
		clients.setClientSecret(authClientDetails.getClientSecret());
		clients.setAccessTokenValiditySeconds(authClientDetails.getAccessTokenValiditySeconds());
		clients.setRefreshTokenValiditySeconds(authClientDetails.getRefreshTokenValiditySeconds());
		List<String> authorizedGrantTypes=new ArrayList<String>();
		String[] authorizedGrantTypesArray=StringUtils.split(authClientDetails.getAuthorizedGrantTypes(),",");
		for (String authorizedGrantType : authorizedGrantTypesArray) {
			authorizedGrantTypes.add(authorizedGrantType);
		}
		clients.setAuthorizedGrantTypes(authorizedGrantTypes);
		List<String> scopes=new ArrayList<String>();
		String[] scopesArray=StringUtils.split(authClientDetails.getScope(),",");
		for (String scope : scopesArray) {
			scopes.add(scope);
		}
		clients.setScope(scopes);
		return clients;
	}

}
