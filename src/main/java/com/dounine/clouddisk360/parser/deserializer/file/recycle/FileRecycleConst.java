package com.dounine.clouddisk360.parser.deserializer.file.recycle;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileRecycleConst extends BaseConst {

	public final String URI_PATH = "/file/asyncRecycle/";
	public final String PATH_NAME = "path[]";
	
	@Override
	public String getUriPath() {
		return URI_PATH;
	}

}
