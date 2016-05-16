package com.dounine.clouddisk360.parser.deserializer.file.search;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileSearchConst extends BaseConst {

	public static final String URI_PATH = "/file/searchList";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String KEY_NAME = "key";
	public static final String ISFPATH_NAME = "is_fpath";


}
