package com.bec.cloud.service.example.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bec.cloud.auth.core.exception.BecExceptionEnum;
import com.bec.cloud.auth.core.support.Result;
import com.bec.cloud.auth.core.utils.OSSUploadUtil;
import com.bec.cloud.auth.core.utils.ResultUtil;

/**
 * @author suruiliang
 * @version 创建时间：2018年4月8日 下午1:32:00
 * @ClassName 类名称
 * @Description 类描述
 */
@RestController
@RequestMapping("/oss")
public class OssController {
	
	@GetMapping("/policy")
	public Result<?> policy() throws UnsupportedEncodingException {
		return ResultUtil.success(OSSUploadUtil.objectPolicy());
	}
	
	@PostMapping(value="/upload")
	public Result<String> upload( HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws IOException {
		if(file.isEmpty()) {
			return ResultUtil.error(BecExceptionEnum.UNKNOWN_EXCEPTION);
		}
		return ResultUtil.success(OSSUploadUtil.uploadFile(file.getInputStream(), OSSUploadUtil.BUCKET, OSSUploadUtil.DIR+"image/",file.getOriginalFilename()));
	}
	
	@DeleteMapping(value="/remove")
	public Result<String> remove(HttpServletRequest request,HttpServletResponse response,String fileUrl) {
		boolean flag=OSSUploadUtil.deleteFile(fileUrl);
		return ResultUtil.success(fileUrl+":删除"+(flag?"成功！":"失败！"));
	}
}
