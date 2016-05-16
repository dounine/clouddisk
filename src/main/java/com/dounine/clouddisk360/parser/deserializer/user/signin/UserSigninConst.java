package com.dounine.clouddisk360.parser.deserializer.user.signin;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class UserSigninConst extends BaseConst {
	
	public static final String URI_PATH = "/user/signin/";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String QID_NAME = "qid";
	public static final String METHOD_KEY = "method";
	public static final String METHOD_VAL = "signin";

	public static final String T_NAME = "t";
	public static final String AJAX_KEY = "1";

}
