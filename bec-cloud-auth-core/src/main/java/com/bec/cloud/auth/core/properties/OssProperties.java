package com.bec.cloud.auth.core.properties;

import lombok.Data;

/**
* @author suruiliang
* @version 创建时间：2018年4月8日 下午12:55:14
* @ClassName 类名称
* @Description 类描述
*/
@Data
public class OssProperties {
	private String endpoint="oss-cn-hangzhou.aliyuncs.com";
	private String accessKeyId="LTAIPh7Vj53Pvf3U";
	private String accessKeySecret="ssbG6hiVdXTxuCxLuuRV2kImdjcTEo";
	private String bucket="bec-test";
	private String dir="file/";
	private Long expireIn=60L;//60秒
}
