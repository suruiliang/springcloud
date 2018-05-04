package com.bec.cloud.auth.core.properties;

import lombok.Data;

@Data
public class Oauth2Properties {
	
	private String jwtSignKey="bec";
	//redis,jwt
	private String storeType;

}
