package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class Customer {
    private Long custId;

    private Long custPid;

    private String custIid;

    private String custCode;

    private String pCustCode;

    private String orgCode;

    private String custMcc;

    private Integer custLevel;

    private Integer custAttr;

    private String custPhone;

    private String custOldPhone;

    private String custName;

    private String custAbbr;

    private String custCompanyName;

    private String custAddress;

    private Integer countyCode;

    private String custEmail;

    private String legalName;

    private String legalIdentity;

    private String legalIdentityDate;

    private Integer custStatus;

    private Integer modifyStatus;

    private Integer delStatus;

    private Date recordTime;

    private Long entryUserId;

    private Long operationUserId;

    private Date operationTime;

}