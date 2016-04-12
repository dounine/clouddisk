package com.dounine.clouddisk360.parser.deserializer.login;

import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.util.URLUtil;

public class LoginResponseHandle extends BaseResponseHandle<Login, LoginParser> implements ResponseHandler<Login> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginResponseHandle.class);

	private static final String[] LOGIN_ERROR_MSGS = {
			"无效的登录",
			"验证码错误请重新输入",
			"登录密码错误，请重新输入",
			"密码不合法",
			"请输入验证码",
			"密码错误次数超限，请24小时后再试"};

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
		int userInfoIndex = result.indexOf(LoginConst.SPLIT_STR);
		if (userInfoIndex > -1) {
			String userInfo = result.substring(userInfoIndex + LoginConst.SPLIT_STR.length(),
					result.lastIndexOf(";</script>") - 2);
			return URLUtil.decode(userInfo);
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("{'errno':401,'errmsg':");
			String decodeResult = URLUtil.decode(result);
			for(String errCode : LOGIN_ERROR_MSGS){
				if (decodeResult.contains(errCode)) {
					sb.append(String.format("'%s'",errCode));
				}
			}
			if(sb.length()==1){
				sb.append("'登录不合法'");
			}
			sb.append("}");
			return sb.toString();
		}
	}

	@Override
	public void saveCookie() {
		parse.getCookieStoreUT().writeCookieStoreToDisk(parse.getHttpClientContext().getCookieStore(),
				LoginConst.COOKIES_NAMES);
	}

}
