package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileRestoreConst extends BaseConst {
	
	public final String URI_PATH = "/fHistory/restore";
	public final String NID_NAME = "nid";
	public final String ID_NAME = "id";
	public final String SOURCE_NAME = "source";
	
	@Override
	public String getUriPath() {
		return URI_PATH;
	}

}
