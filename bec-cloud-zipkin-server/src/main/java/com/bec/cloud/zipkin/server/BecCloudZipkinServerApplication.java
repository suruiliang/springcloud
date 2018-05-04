package com.bec.cloud.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.EnableZipkinServer;




/**
 * @author suruiliang
 * @version 创建时间：2018年5月2日 下午2:00:12
 * @ClassName 类名称
 * @Description 类描述
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class BecCloudZipkinServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BecCloudZipkinServerApplication.class, args);
	}
}
