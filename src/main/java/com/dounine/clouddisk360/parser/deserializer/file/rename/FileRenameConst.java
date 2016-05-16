package com.dounine.clouddisk360.parser.deserializer.file.rename;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileRenameConst extends BaseConst {

	public static final String URI_PATH = "/file/rename";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String PATH_NAME = "path";
	public static final String NEWPATH_NAME = "newpath";
	public static final String NID_NAME = "nid";

}
