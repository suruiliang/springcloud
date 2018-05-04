package com.bec.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author suruiliang
 * @version 创建时间：2018年5月2日 上午11:31:32
 * @ClassName 类名称
 * @Description 类描述
 */
@SpringBootApplication
@EnableEurekaServer
public class BecCloudEurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BecCloudEurekaServerApplication.class, args);
	}
}
