package com.bec.cloud.service.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
* @author suruiliang
* @version 创建时间：2018年5月2日 下午12:59:10
* @ClassName 类名称
* @Description 类描述
*/
@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
public class BecCloudServiceDemo2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(BecCloudServiceDemo2Application.class, args);
	}
}
