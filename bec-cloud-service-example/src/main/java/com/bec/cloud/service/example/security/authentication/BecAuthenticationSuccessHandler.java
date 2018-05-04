package com.bec.cloud.service.example.security.authentication;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bec.cloud.auth.core.properties.SecurityProperties;
import com.bec.cloud.auth.core.utils.ResultUtil;
import com.bec.cloud.service.example.mapper.AuthLogMapper;
import com.bec.cloud.service.example.model.AuthLog;
import com.bec.cloud.service.example.model.AuthOrganization;
import com.bec.cloud.service.example.model.UserInfo;
import com.bec.cloud.service.example.utils.UserInfoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(value="becAuthenticationSuccessHandler")
public class BecAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private UserInfoUtil userInfoUtil;
	@Autowired
	private AuthLogMapper authLogMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];

		ClientDetails clientDetails=clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails==null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:"+clientId);
		}else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:"+clientId);
		}

		TokenRequest tokenRequest=new TokenRequest(Collections.emptyMap(), clientId, clientDetails.getScope(), "custom");

		OAuth2Request oAuth2Request=tokenRequest.createOAuth2Request(clientDetails);

		OAuth2Authentication oAuth2Authentication=new OAuth2Authentication(oAuth2Request, authentication);

		OAuth2AccessToken token=authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
		//插入登录日志
		try {
			insertAuthLog(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("插入登录日志报错！");
		}
		response.setContentType("application/json;charset=UTF-8");
		//		response.getWriter().write(objectMapper.writeValueAsString(token));
		response.getWriter().write(objectMapper.writeValueAsString(ResultUtil.success(token)));

	}	
	private void insertAuthLog(HttpServletRequest request) {
		AuthLog authLog=new AuthLog();
		UserInfo userInfo=userInfoUtil.userInfo();
		authLog.setCustCode(userInfo.getCustCode());
		authLog.setCustName("");// TODO customer
		authLog.setLoginIp(request.getRemoteHost());
		authLog.setLoginTime(new Date());
		authLog.setMac(request.getParameter("mac"));
		authLog.setOrgCode(userInfo.getOrgCode());
		authLog.setOrgName("");
		if (userInfo.getAuthOrganizations()!=null&&userInfo.getAuthOrganizations().size()>0) {
			String orgName="";
			for (AuthOrganization ao : userInfo.getAuthOrganizations()) {
				orgName.join(",", ao.getOrgName());
			}
			authLog.setOrgName(orgName.substring(1));
		}
		authLog.setSoftType(Integer.valueOf(request.getParameter("softType")+""));
		authLog.setUserId(userInfo.getUserId());
		authLog.setUserName(userInfo.getUserName());
		authLog.setVersion(request.getParameter("version"));
		authLog.setVisitModule(securityProperties.getVisitModule());
		authLogMapper.insertSelective(authLog);
	}
	private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
			throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new BadCredentialsException(
					"Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

}
