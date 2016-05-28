package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileRecycleConst extends BaseConst {

	public static final String URI_PATH = "/file/asyncRecycle/";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String PATH_NAME = "path[]";

}
