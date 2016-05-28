package com.dounine.clouddisk360.parser.deserializer.file.trends;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileTrendsListConst extends BaseConst {

	public static final String PATH_NAME = "path";
	public static final String NEWPATH_NAME = "newpath";
	public static final String NID_NAME = "nid";
    public final String URI_PATH = "/trends/getTrendsList";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}



}
