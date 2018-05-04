package com.bec.cloud.service.example.model;

import java.util.Date;

public class AuthLog {
    private Long logId;

    private String loginIp;

    private Date loginTime;

    private Long userId;

    private String custNum;

    private String orgNum;

    private String version;

    private String mac;

    private Integer softType;

    private String visitModule;

    private String userName;

    private String custName;

    private String orgName;

    private Date recordTime;

    private Date opretionTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum == null ? null : custNum.trim();
    }

    public String getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(String orgNum) {
        this.orgNum = orgNum == null ? null : orgNum.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Integer getSoftType() {
        return softType;
    }

    public void setSoftType(Integer softType) {
        this.softType = softType;
    }

    public String getVisitModule() {
        return visitModule;
    }

    public void setVisitModule(String visitModule) {
        this.visitModule = visitModule == null ? null : visitModule.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getOpretionTime() {
        return opretionTime;
    }

    public void setOpretionTime(Date opretionTime) {
        this.opretionTime = opretionTime;
    }
}