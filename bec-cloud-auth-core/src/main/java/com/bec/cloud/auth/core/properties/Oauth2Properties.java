package com.bec.cloud.auth.core.properties;

import lombok.Data;

@Data
public class Oauth2Properties {
	
	private String jwtSignKey="bec";
	//redis,jwt
	private String storeType;
	//0  default，1 limit one
	private Integer strategy=0;

}
