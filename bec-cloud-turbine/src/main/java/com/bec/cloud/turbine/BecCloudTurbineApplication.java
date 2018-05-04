package com.bec.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author suruiliang
 * @version 创建时间：2018年5月2日 下午5:30:47
 * @ClassName 类名称
 * @Description 类描述
 */
@SpringBootApplication
@EnableTurbine
public class BecCloudTurbineApplication {
	public static void main(String[] args) {
		SpringApplication.run(BecCloudTurbineApplication.class, args);
	}
}
