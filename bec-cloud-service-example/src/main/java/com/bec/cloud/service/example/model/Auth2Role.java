package com.bec.cloud.service.example.model;

import java.util.Date;

import lombok.Data;

@Data
public class Auth2Role {
    private String url;
    private String method;
    private String roles;
}