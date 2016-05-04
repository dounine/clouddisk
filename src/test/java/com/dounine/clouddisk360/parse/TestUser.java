package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.store.BasePathCommon;

public final class TestUser {
	
	static{
		BasePathCommon.BASE_PATH = "/Users/huanghuanlai/Desktop/clouddisk/";
	}

	public static final LoginUserToken LOGIN_USER_TOKEN = new LoginUserToken("102535481@qq.com",
			"",false);

}
