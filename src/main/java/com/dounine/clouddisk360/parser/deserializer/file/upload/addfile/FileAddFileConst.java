package com.dounine.clouddisk360.parser.deserializer.file.upload.addfile;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileAddFileConst extends BaseConst {

	public static final String URI_PATH = "/upload/addfile";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String TK_NAME = "tk";
	public static final String NID_NAME = "nid";

}
