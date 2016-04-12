package com.dounine.clouddisk360.parser.deserializer.captcha;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class Captcha extends BaseDes {

	private Boolean captchaFlag;
	private String captchaUrl;

	public Boolean getCaptchaFlag() {
		return captchaFlag;
	}

	public void setCaptchaFlag(Boolean captchaFlag) {
		this.captchaFlag = captchaFlag;
	}

	public String getCaptchaUrl() {
		return captchaUrl;
	}

	public void setCaptchaUrl(String captchaUrl) {
		this.captchaUrl = captchaUrl;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
