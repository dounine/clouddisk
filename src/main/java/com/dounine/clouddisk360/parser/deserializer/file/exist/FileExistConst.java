package com.dounine.clouddisk360.parser.deserializer.file.exist;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileExistConst extends BaseConst {

	public static final String URI_PATH = "/file/detectFileExists";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String DIR_NAME = "dir";
	public static final String FNAME_NAME = "fname[]";

}
