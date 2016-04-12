package com.dounine.clouddisk360.parser.deserializer.authtoken;

import com.dounine.clouddisk360.parser.AuthTokenParser;
import com.dounine.clouddisk360.parser.UserInfoParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.user.info.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AuthTokenResponseHandle extends BaseResponseHandle<AuthToken, AuthTokenParser>
		implements ResponseHandler<AuthToken> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenResponseHandle.class);

	public AuthTokenResponseHandle(AuthTokenParser parse) {
		super(parse);
	}

	@Override
	public AuthToken handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		List<Cookie> cookies = parse.getHttpClientContext().getCookieStore().getCookies();
		cookies.stream().filter(c -> c.getName().equals(AuthTokenConst.COOKIE_NAME));
		Optional<Cookie> authCookieOptionsal = cookies.stream().filter(c -> c.getName().equals(AuthTokenConst.COOKIE_NAME)).findFirst();
		AuthToken authToken = null;
		if(authCookieOptionsal.isPresent()){
			authToken = this.desializer(authCookieOptionsal.get().getValue());
			saveCookie();
		}
		if(null==parse.getHttpClientContext().getAttribute(AuthTokenConst.LOGIN_USER_INFO)){
			parse.setBianaryFilename("user/userInfo.txt");
			Login loginUser = parse.readObjForDisk(Login.class);
			if(null==loginUser||(null!=loginUser&& StringUtils.isBlank(loginUser.getQid()))){
				UserInfoParser userInfoParser = new UserInfoParser(parse.getLoginUserToken());//用户信息解析器
				UserInfo userInfo = userInfoParser.parse();
				if(0==userInfo.getErrno()){
					loginUser = new Login();
					loginUser.setQid(userInfo.getQid());
					loginUser.setErrno(userInfo.getErrno());
					loginUser.setCddmsg(userInfo.getCddmsg());
					loginUser.setErrmsg(userInfo.getErrmsg());
					loginUser.setLoginEmail(userInfo.getLogin_email());
					loginUser.setUserName(userInfo.getUserName());
					loginUser.setImageUrl(userInfo.getImg_url());
					loginUser.setCrumb(userInfo.getCrumb());
					loginUser.setNickName(userInfo.getNickname());
					loginUser.setType(userInfo.getType());
					parse.writeObjToDisk(loginUser);
				}else{
					LOGGER.error("在authToken中初始化云盘信息失败");
				}
			}
			parse.getHttpClientContext().setAttribute(AuthTokenConst.LOGIN_USER_INFO, loginUser);
		}
		return authToken;
	}

	public String disassemblyResult(String result) {
		return String.format("{\"token\":\"%s\"}", result);
	}

	@Override
	public void saveCookie() {
		parse.getCookieStoreUT().writeCookieStoreToDisk(parse.getHttpClientContext().getCookieStore(),
				AuthTokenConst.TOKEN_NAME, true);
	}

}
