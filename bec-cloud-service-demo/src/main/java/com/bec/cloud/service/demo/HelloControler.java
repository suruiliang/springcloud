package com.bec.cloud.service.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suruiliang
 * @version 创建时间：2018年5月2日 下午1:15:01
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
public class HelloControler {
	@Autowired
	HelloService helloService;
	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name){
		return helloService.helloService(name);
	}
}
