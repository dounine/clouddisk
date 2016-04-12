package com.dounine.clouddisk360.parser.deserializer;

import com.dounine.clouddisk360.exception.CloudDiskException;

public final class MsgConst {

	public static final CloudDiskException CONFIG_COOKIESTORE_MSG = new CloudDiskException("请配置非空config对象");
	public static final CloudDiskException BASE_PATH_MSG = new CloudDiskException("请初始化BasePathCommon中的basePath目录所在位置");
	
	public static final String HOST_VALUE_NOT_NULL = "DifferPress中的RedirectUrl为空";
}
