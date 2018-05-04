package com.bec.cloud.service.example.security.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.bec.cloud.service.example.mapper.AuthUserMapper;
import com.bec.cloud.service.example.model.AuthUser;

public class BecJwtTokenEnhancer implements TokenEnhancer{
	@Autowired
	private AuthUserMapper authUserMapper;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info=new HashMap<String, Object>();
		AuthUser authUser=authUserMapper.selectAuthUserByUsername(authentication.getName());
		if (authUser!=null) {
			info.put("custId",authUser.getCustId());
			info.put("headPortrait",authUser.getHeadPortrait());
			info.put("roleId",authUser.getRoleId());
			info.put("sysId",authUser.getSysId());
			info.put("userId",authUser.getUserId());
			info.put("realName",authUser.getRealName());
			info.put("userStatus",authUser.getUserStatus());
		}
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
