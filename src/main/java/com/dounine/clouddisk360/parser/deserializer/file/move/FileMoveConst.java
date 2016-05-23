package com.dounine.clouddisk360.parser.deserializer.file.move;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileMoveConst extends BaseConst {

	public final String URI_PATH = "/file/move/";
	public final String PATH_NAME = "path[]";
	public final String NEWPATH_NAME = "newpath";
	public static final String NID_NAME = "nid";
	
	@Override
	public String getUriPath() {
		return URI_PATH;
	}

}
