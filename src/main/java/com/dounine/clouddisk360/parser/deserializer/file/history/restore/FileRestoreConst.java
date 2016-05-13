package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileRestoreConst extends BaseConst {
	
	public static final String URI_PATH = "/fHistory/restore";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String NID_NAME = "nid";
	public static final String ID_NAME = "id";
	public static final String SOURCE_NAME = "source";

}
