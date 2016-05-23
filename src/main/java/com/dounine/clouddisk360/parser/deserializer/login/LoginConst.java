package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.parser.deserializer.BaseConst;

public class LoginConst extends BaseConst {

	public static final String USER_INFO_PATH_NAME = "user/userInfo.txt";
	public static final String URI_PATH = "https://login.360.cn/";

	public static final String LOGIN_URL = "https://login.360.cn/";
	
	public static final String QUCRYPTCODE_NAME = "quCryptCode";

	public static final String REQUESTSCEMA_KEY = "requestScema";
	public static final String REQUESTSCEMA_VAL = "https";

	public static final String LM_KEY = "lm";
	public static final String LM_VAL = "0";

	public static final String CAPTFLAG_KEY = "captFlag";
	public static final String CAPTFLAG_VAL = "1";

	public static final String RTYPE_KEY = "rtype";
	public static final String RTYPE_VAL = "data";

	public static final String VALIDATELM_KEY = "validatelm";
	public static final String VALIDATELM_VAL = "0";

	/**
	 * 记住我登录
	 */
	public static final String ISKEEPALIVE_KEY = "isKeepAlive";
	public static final String ISKEEPALIVE_VAL = "1";

	public static final String CAPTCHAAPP_KEY = "captchaApp";
	public static final String CAPTCHAAPP_VAL = "i360";

	public static final String TYPE_KEY = "type";
	public static final String TYPE_VAL = "normal";

	public static final String CAPTCHA_KEY = "captcha";

	public static final String PROXY_KEY = "proxy";
	public static final String PROXY_VAL = "http://yunpan.360.cn/psp_jump.html";

	public static final String M_KEY = "m";
	public static final String M_VAL = "login";

	public static final String TOKEN_NAME = "token";
	public static final String PASSWORD_NAME = "password";
	public static final String USERNAME_NAME = "userName";
	public static final String ACCOUNT_NAME = "account";

	public static final String ORIGIN_KEY = "Origin";
	public static final String ORIGIN_VAL = "http://yunpan.360.cn";

	public static final String SPLIT_STR = "userinfo=";

	public static final String[] COOKIES_NAMES = { "Q", "T" };

}
