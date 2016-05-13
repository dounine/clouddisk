package com.dounine.clouddisk360.parser.deserializer.file.download.dladdress;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileDownloadAddressConst extends BaseConst {

	public final String URI_PATH = "/file/download/";

	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	public static final String NID_NAME = "nid";
	public static final String FNAME_NAME = "fname";

}
