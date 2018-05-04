package com.bec.cloud.service.example.model;

import java.util.Date;

public class AuthOrganization {
    private Long orgId;

    private String orgName;

    private String orgAbbr;

    private String orgCode;

    private String orgPhone;

    private String orgIid;

    private String pOrgCode;

    private String legalName;

    private String legalPhone;

    private String busLicense;

    private String busLicensePhoto;

    private String busAddress;

    private String registeredAddress;

    private Integer orgStatus;

    private Integer delStatus;

    private Date recordTime;

    private Long entryUserId;

    private Long operationUserId;

    private Date opretionTime;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgAbbr() {
        return orgAbbr;
    }

    public void setOrgAbbr(String orgAbbr) {
        this.orgAbbr = orgAbbr == null ? null : orgAbbr.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone == null ? null : orgPhone.trim();
    }

    public String getOrgIid() {
        return orgIid;
    }

    public void setOrgIid(String orgIid) {
        this.orgIid = orgIid == null ? null : orgIid.trim();
    }

    public String getpOrgCode() {
        return pOrgCode;
    }

    public void setpOrgCode(String pOrgCode) {
        this.pOrgCode = pOrgCode == null ? null : pOrgCode.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
    }

    public String getBusLicense() {
        return busLicense;
    }

    public void setBusLicense(String busLicense) {
        this.busLicense = busLicense == null ? null : busLicense.trim();
    }

    public String getBusLicensePhoto() {
        return busLicensePhoto;
    }

    public void setBusLicensePhoto(String busLicensePhoto) {
        this.busLicensePhoto = busLicensePhoto == null ? null : busLicensePhoto.trim();
    }

    public String getBusAddress() {
        return busAddress;
    }

    public void setBusAddress(String busAddress) {
        this.busAddress = busAddress == null ? null : busAddress.trim();
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress == null ? null : registeredAddress.trim();
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getEntryUserId() {
        return entryUserId;
    }

    public void setEntryUserId(Long entryUserId) {
        this.entryUserId = entryUserId;
    }

    public Long getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    public Date getOpretionTime() {
        return opretionTime;
    }

    public void setOpretionTime(Date opretionTime) {
        this.opretionTime = opretionTime;
    }
}