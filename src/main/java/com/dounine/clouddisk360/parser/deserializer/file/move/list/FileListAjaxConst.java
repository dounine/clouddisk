package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileListAjaxConst extends BaseConst {
	
	public static final String URI_PATH = "/file/listAjax";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String PATH_NAME = "path";
	public static final String ID_NAME = "id";
	public static final String NID_NAME = "nid";

}
