package com.bec.cloud.service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
* @author suruiliang
* @version 创建时间：2018年5月2日 下午1:10:54
* @ClassName 类名称
* @Description 类描述
*/
@Service
public class HelloService {
	@Autowired
    RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "hiError")
    public String helloService(String name) {
        return restTemplate.getForObject("http://service-feign/hello?name="+name,String.class);
    }

    public String hiError(String name) {
        return "hello,"+name+",sorry,error!";
    }
}
