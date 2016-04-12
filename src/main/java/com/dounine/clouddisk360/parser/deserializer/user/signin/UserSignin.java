package com.dounine.clouddisk360.parser.deserializer.user.signin;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.BaseDes;

public class UserSignin extends BaseDes {

	private UserSigninData data;

	public UserSigninData getData() {
		return data;
	}

	public void setData(UserSigninData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, true);
	}

}
