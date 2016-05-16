package com.dounine.clouddisk360.parser.deserializer.file.list;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public final class FileListConst extends BaseConst {

	
	public static final String REQUESTSCEMA_KEY = "requestScema";
	public static final String REQUESTSCEMA_VAL = "https";
	public static final String FIELD_VAL = "file_name";
	public static final String PAGE_NAME = "page";
	public static final String M_KEY = "m";
	public static final String M_VAL = "getToken";
	public static final String USERNAME_NAME = "userName";
	
	public final String URI_PATH = "/file/list";
	public final String TYPE_NAME = "type";
	public final String PATH_NAME = "path";
	public final String ORDER_NAME = "order";
	public final String FIELD_KEY = "field";
	public final String PAGE_SIZE_NAME = "page_size";
	
	@Override
	public String getUriPath() {
		return URI_PATH;
	}

	

}
