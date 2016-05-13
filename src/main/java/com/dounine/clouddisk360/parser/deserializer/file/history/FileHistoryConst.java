package com.dounine.clouddisk360.parser.deserializer.file.history;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileHistoryConst extends BaseConst {

	public static final String URI_PATH = "/fHistory/getFileHistory/";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String NID_NAME = "nid";
	public static final String HIS_NID_NAME = "his_nid";
	public static final String START_NAME = "start";
	public static final String NUM_NAME = "num";
	public static final String SOURCE_NAME = "source";

}
