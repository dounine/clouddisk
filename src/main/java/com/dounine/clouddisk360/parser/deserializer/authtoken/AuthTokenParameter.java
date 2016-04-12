package com.dounine.clouddisk360.parser.deserializer.authtoken;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class AuthTokenParameter extends BaseParameter{

	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
