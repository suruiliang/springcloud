package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthClientDetails {
    private String clientId;

    private String resourceIds;

    private Boolean secretRequired;

    private String clientSecret;

    private Boolean scoped;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private String authorities;

    private Integer refreshTokenValiditySeconds;

    private Integer accessTokenValiditySeconds;

    private Boolean autoApprove;

    private String additionalInformation;

    private Date recordTime;

}