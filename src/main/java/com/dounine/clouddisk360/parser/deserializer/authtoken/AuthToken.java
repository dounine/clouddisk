package com.dounine.clouddisk360.parser.deserializer.authtoken;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class AuthToken extends BaseDes {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
