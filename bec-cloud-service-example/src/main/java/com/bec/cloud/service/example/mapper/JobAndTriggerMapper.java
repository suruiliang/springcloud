package com.bec.cloud.service.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.bec.cloud.service.example.model.JobAndTrigger;

/**
* @author suruiliang
* @version 创建时间：2018年5月23日 下午1:51:43
* @ClassName 类名称
* @Description 类描述
*/
@Component(value="_jobAndTriggerMapper")
@Mapper
public interface JobAndTriggerMapper {
	public List<JobAndTrigger> getJobAndTriggerDetails();
}
