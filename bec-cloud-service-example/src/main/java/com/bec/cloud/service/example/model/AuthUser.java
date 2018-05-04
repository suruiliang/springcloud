package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthUser {
	
	private Long userId;
	
	private Long custId;
	
	private Integer sysId;
	
	private Integer roleId;
	
	private Integer userStatus;
	
	private Integer failedNum;
	
	private Date recordTime;
	
	private Date lastLoginTime;
	
	private Date loginTime;
	
	private String userName;
	
	private String realName;
	
	private String userPasswd;
	
	private String userSalt;
	
	private String userIdentity;
	
	private String loginIp;
	
	private String lastLoginIp;
	
	private String headPortrait;

}