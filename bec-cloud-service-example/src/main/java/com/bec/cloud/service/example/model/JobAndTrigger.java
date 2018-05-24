package com.bec.cloud.service.example.model;

import java.math.BigInteger;

import lombok.Data;

/**
* @author suruiliang
* @version 创建时间：2018年5月23日 下午1:49:52
* @ClassName 类名称
* @Description 类描述
*/
@Data
public class JobAndTrigger {
	private String JOB_NAME;
	private String JOB_GROUP;
	private String JOB_CLASS_NAME;
	private String TRIGGER_NAME;
	private String TRIGGER_GROUP;
	private String TRIGGER_STATE;
	private BigInteger REPEAT_INTERVAL;
	private BigInteger TIMES_TRIGGERED;
	private String CRON_EXPRESSION;
	private String TIME_ZONE_ID;
}
