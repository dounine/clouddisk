package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class LoginParameter extends BaseParameter {

	private String captchaValue;

	/**
	 * 以下是配置所用的
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}

}
