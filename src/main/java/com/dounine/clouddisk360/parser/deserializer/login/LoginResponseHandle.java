package com.dounine.clouddisk360.parser.deserializer.login;

import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.util.URLUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;

public class LoginResponseHandle extends BaseResponseHandle<Login, LoginParser> implements ResponseHandler<Login> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginResponseHandle.class);

	public LoginResponseHandle(final LoginParser parse) {
		super(parse);
	}

	@Override
	public Login desializer(String result) {
		final Login login = super.desializer(result);
		if(login.getErrno()==0){
			parse.setLoginInfo(login);//把用户信息放到上下文当中,以便随时获取
			parse.setBinaryFilename(LoginConst.USER_INFO_PATH_NAME);
			parse.writeObjToDisk(login);
		}
		return login;
	}

	@Override
	public String disassemblyResult(String result) {
		String resultLocal = URLUtil.decode(result);
		final Matcher matcher = VAL_PAT.matcher(resultLocal);
		final Matcher errMatcher = ERR_VAL_PAT.matcher(resultLocal);
		if(errMatcher.find()){
			final String msg = errMatcher.group();
			final String[] vs = msg.split("&");
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
