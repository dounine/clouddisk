package com.dounine.clouddisk360.parser.deserializer.passport;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class Passport extends BaseDes {

	private String captchaUrl;

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
