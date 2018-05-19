package com.bec.cloud.service.example.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserInfo {
    private Long userId;

    private String custCode;

    private String orgCode;

    private String userName;

    private String userOldName;

    private String realName;

    private String userPasswd;

    private String userIdentity;

    private String loginIp;

    private Date loginTime;

    private Integer userStatus;

    private Integer delStatus;

    private Date recordTime;

    private Long entryUserId;

    private Long operationUserId;

    private Date operationTime;

    private String userPhone;
    
    private String dotCode;
    
    private List<AuthRole> authRoles;

    private List<Organization> organizations;
    
    private List<Customer> customers;
    
    //TODO CustDots

}