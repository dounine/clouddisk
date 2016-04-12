package com.dounine.clouddisk360.parser.deserializer.file.history;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileHistoryConst extends BaseConst {

	public final String URI_PATH = "/fHistory/getFileHistory/";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public final String NID_NAME = "nid";
	public final String HIS_NID_NAME = "his_nid";
	public final String START_NAME = "start";
	public final String NUM_NAME = "num";
	public final String SOURCE_NAME = "source";

}
