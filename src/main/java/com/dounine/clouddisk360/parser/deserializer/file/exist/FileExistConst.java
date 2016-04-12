package com.dounine.clouddisk360.parser.deserializer.file.exist;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileExistConst extends BaseConst {

	public final String URI_PATH = "/file/detectFileExists";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public final String DIR_NAME = "dir";
	public final String FNAME_NAME = "fname[]";

}
