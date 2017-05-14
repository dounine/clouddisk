package com.dounine.clouddisk360.parser.deserializer;

import com.dounine.clouddisk360.parser.deserializer.user.IBaseConst;

public class BaseConst implements IBaseConst{

	public static final String QID_NAME = "qid";
	public static final String SRC_KEY = "src";
	public static final String SRC_VAL = "pcw_cloud";

	public static final String FROM_KEY = "from";
	public static final String FROM_VAL = "pcw_cloud";

	public static final String CHARSET_KEY = "charset";
	public static final String CHARSET_VAL = "UTF-8";

	public static final String REQUESTSCEMA_KEY = "requestScema";
	public static final String REQUESTSCEMA_VAL = "http";

	public static final String O_KEY = "o";
	public static final String O_VAL = "sso";

	public static final String CONNECTION_KEY = "Connection";
	public static final String CONNECTION_VAL = "keep-alive";

	public static final String CONTENT_TYPE_KEY = "Content-type";
	public static final String CONTENT_TYPE_VAL = "application/x-www-form-urlencoded UTF-8";

	public static final String REFERER_KEY = "Referer";
	public static final String REFERER_VAL = "http://yunpan.360.cn/";

	public static final String ORIGIN_KEY = "Origin";
	public static final String AJAX_KEY = "ajax";
	public static final String AJAX_VAL = "1";

	public static final String USER_AGENT_KEY = "User-Agent";
	public static final String USER_AGENT_VAL = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36";

	public static final String ACCEPT_KEY = "Accept";
	public static final String ACCEPT_VAL = "*/*";

	public static final String ACCEPT_ENCODING_KEY = "Accept-Encoding";
	public static final String ACCEPT_ENCODING_VAL = "gzip, deflate, sdch";

	public static final String ACCEPT_LANGUAGE_KEY = "Accept-Language";
	public static final String ACCEPT_LANGUAGE_VAL = "zh-CN,zh;q=0.8,en;q=0.6";

	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";

	public static final String X_REQUESTED_WITH_KEY = "X-Requested-With";
	public static final String X_REQUESTED_WITH_VAL = "XMLHttpRequest";

	public static final String HOST_KEY = "Host";
	public static final String HOST_VAL = "login.360.cn";

	public static final String COOKIES_PATH_NAME = "cookies.txt";
	public final String PAGE_NAME = "page";
	public final String PAGE_SIZE_NAME = "page_size";

	public static final String COOKIE_NAME = "cookie";
	public static final String[] BASE_COOKIES_VALUES = { "Q", "T" };

	public static final String REQUEST_HOST_NAME = "request-host";
	public static final String REQUEST_SCHEME_HOST_NAME = "request-scheme-host";
	public static final String REQUEST_REFERER_NAME = "request-referer";
	public static final String REQUEST_ORIGIN_NAME = "request-origin";

}
