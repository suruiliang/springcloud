package com.bec.cloud.auth.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.bec.cloud.auth.core.validate.code.ValidateCode;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ImageCode extends ValidateCode{
	private static final long serialVersionUID = 3976059900191305701L;
	
	private BufferedImage image;
	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code,expireIn);
		this.image = image;
	}
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code,expireTime);
		this.image = image;
	}
}
