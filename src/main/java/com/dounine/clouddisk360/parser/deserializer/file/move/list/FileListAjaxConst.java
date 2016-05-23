package com.dounine.clouddisk360.parser.deserializer.file.move.list;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileListAjaxConst extends BaseConst {
	
	public final String URI_PATH = "/file/listAjax";
	public final String PATH_NAME = "path";
	public final String ID_NAME = "id";
	public final String NID_NAME = "nid";
	
	@Override
	public String getUriPath() {
		return URI_PATH;
	}

}
