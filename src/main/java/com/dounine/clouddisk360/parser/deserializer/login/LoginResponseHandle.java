package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.util.URLUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginResponseHandle extends BaseResponseHandle<Login, LoginParser> implements ResponseHandler<Login> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginResponseHandle.class);

	public static final Pattern VAL_PAT = Pattern.compile("[{].*[}]");
	public static final Pattern ERR_VAL_PAT = Pattern.compile("errno=\\d{2,}&errmsg=[\\u0391-\\uFFE5]{2,}");//查找错误信息

	public LoginResponseHandle(LoginParser parse) {
		super(parse);
	}

	@Override
	public Login desializer(String result) {
		Login login = super.desializer(result);
		if(login.getErrno()!=0){
			return login;
		}
		parse.setLoginInfo(login);//把用户信息放到上下文当中,以便随时获取
		parse.setBianaryFilename(LoginConst.USER_INFO_PATH_NAME);
		parse.writeObjToDisk(login);
		return login;
	}

	@Override
	public String disassemblyResult(String result) {
		result = URLUtil.decode(result);
		Matcher matcher = VAL_PAT.matcher(result);
		Matcher errMatcher = ERR_VAL_PAT.matcher(result);
		if(errMatcher.find()){
			String msg = errMatcher.group();
			String[] vs = msg.split("&");
			return String.format("{'errno':%d,'errmsg':'%s'}",Integer.parseInt(vs[0].split("=")[1]),vs[1].split("=")[1]);
		}else if(matcher.find()){
			return matcher.group();
		}
		LOGGER.error("登录错误信息不识别");
		return StringUtils.EMPTY;
	}

	@Override
	public void saveCookie() {
		parse.getCookieStoreUT().writeCookieStoreToDisk(parse.getHttpClientContext().getCookieStore(),
				LoginConst.COOKIES_NAMES);
	}

}
