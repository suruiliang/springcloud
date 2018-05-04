package com.bec.cloud.service.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suruiliang
 * @version 创建时间：2018年4月12日 下午1:08:29
 * @ClassName 类名称
 * @Description 类描述
 */
@SpringBootApplication(scanBasePackages="com.bec")
//@EnableAutoConfiguration
//@EnableEurekaClient
//@EnableHystrix
//@EnableHystrixDashboard
public class BecCloudServiceExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(BecCloudServiceExampleApplication.class, args);
	}
}
