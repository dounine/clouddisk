package com.dounine.clouddisk360.parser.deserializer.user.size;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class UserSizeConst extends BaseConst {

	public static final String QID_NAME = "qid";
	public static final String METHOD_KEY = "method";
	public static final String METHOD_VAL = "signin";
	public final String URI_PATH = "/user/getsize/?ajax=1";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}



}
