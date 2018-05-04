package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthLog {
    private Long logId;

    private String loginIp;

    private Date loginTime;

    private Long userId;

    private String custCode;

    private String orgCode;

    private String version;

    private String mac;

    private Integer softType;

    private String visitModule;

    private String userName;

    private String custName;

    private String orgName;

    private Date recordTime;

    private Date opretionTime;

}