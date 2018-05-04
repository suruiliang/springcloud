package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthRole {
    private Integer roleId;

    private Integer rolePid;

    private String orgCode;

    private String roleName;

    private String roleCode;

    private Integer roleStatus;

    private Integer delStatus;

    private Date recordTime;

    private Long entryUserId;

    private Long operationUserId;

    private Date opretionTime;

}