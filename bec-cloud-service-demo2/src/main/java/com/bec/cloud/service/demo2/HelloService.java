package com.bec.cloud.service.demo2;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author suruiliang
* @version 创建时间：2018年5月2日 下午1:10:54
* @ClassName 类名称
* @Description 类描述
*/
@FeignClient(value = "service-ribbon",fallback = HelloServiceHystric.class)
public interface HelloService{
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String helloService(@RequestParam(value = "name") String name);
}

class HelloServiceHystric implements HelloService{
	@Override
	public String helloService(String name) {
		return "error "+name;
	}
}