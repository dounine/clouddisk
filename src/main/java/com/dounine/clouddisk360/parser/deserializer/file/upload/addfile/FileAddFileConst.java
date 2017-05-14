package com.dounine.clouddisk360.parser.deserializer.file.upload.addfile;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileAddFileConst extends BaseConst {

    public static final String NID_NAME = "nid";
    public final String URI_PATH = "/upload/addfile";
	public final String TK_NAME = "tk";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}



}
