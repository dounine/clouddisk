package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.token.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

@Parse("登录令牌")
public class UserTokenParser extends
		BaseParser<HttpGet, UserToken, UserTokenConst, UserTokenParameter, UserTokenRequestInterceptor, UserTokenResponseHandle,UserTokenParser> {

	public UserTokenParser(){
		super();
	}
	public UserTokenParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	public HttpGet initRequest(final UserTokenParameter parameter) {
		HttpGet request = null;
		try {
			final URIBuilder uri = new URIBuilder(CONST.URI_PATH);
			uri.setParameter(CONST.SRC_KEY, CONST.SRC_VAL);
			uri.setParameter(CONST.FROM_KEY, CONST.FROM_VAL);
			uri.setParameter(CONST.CHARSET_KEY, CONST.CHARSET_VAL);
			uri.setParameter(CONST.REQUESTSCEMA_KEY, CONST.REQUESTSCEMA_VAL);
			uri.setParameter(CONST.O_KEY, CONST.O_VAL);
			uri.setParameter(CONST.M_KEY, CONST.M_VAL);
			uri.setParameter(CONST.USERNAME_NAME, loginUserToken.getAccount());
			request = new HttpGet(uri.build());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return request;
	}

}
