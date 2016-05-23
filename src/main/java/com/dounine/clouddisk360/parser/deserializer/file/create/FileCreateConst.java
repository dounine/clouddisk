package com.dounine.clouddisk360.parser.deserializer.file.create;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileCreateConst extends BaseConst {

	public final String URI_PATH = "/file/mkdir";
	public static final String REQUESTSCEMA_KEY = "requestScema";
	public static final String REQUESTSCEMA_VAL = "https";

	public static final String M_KEY = "m";
	public static final String M_VAL = "getToken";

	public static final String USERNAME_NAME = "userName";

	public final String PATH_NAME = "path";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

}
