package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.info.*;
import com.dounine.clouddisk360.pool.PoolingHttpClientConnection;
import com.dounine.clouddisk360.util.MD5Util;
import com.dounine.clouddisk360.util.TimeUtil;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

@Parse("用户信息")
@Dependency(depends={DifferPressParser.class})
@DependResult(customInit = false, result = UserInfo.class)
public class UserInfoParser extends
		BaseParser<HttpGet, UserInfo, UserInfoConst, UserInfoParameter, UserInfoRequestInterceptor, UserInfoResponseHandle,UserInfoParser> {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserInfoParser.class);
	public UserInfoParser(){
		super();
	}
	public UserInfoParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	public static void main(String[] args){
		LOGGER.info(MD5Util.MD5("BJIKE..++"));
	}

	@Override
	public HttpGet initRequest(final UserInfoParameter userInfoParameter) {
		try {
			final URIBuilder uriBuilder = new URIBuilder(CONST.URI_PATH);
			uriBuilder.addParameter(CONST.SRC_KEY, CONST.SRC_VAL);
			uriBuilder.addParameter(CONST.FROM_KEY, CONST.FROM_VAL);
			uriBuilder.addParameter(CONST.CHARSET_KEY, CONST.CHARSET_VAL);
			uriBuilder.addParameter(CONST.METHOD_KEY, CONST.METHOD_VAL);
			uriBuilder.addParameter(CONST.REQUESTSCEMA_KEY, CONST.REQUESTSCEMA_VAL);
			uriBuilder.addParameter(CONST.O_KEY, CONST.O_VAL);
			uriBuilder.addParameter(CONST.SHOW_NAME_FLAG_NAME, CONST.SHOW_NAME_FLAG_VALUE);
			uriBuilder.addParameter(CONST.HEAD_TYPE_NAME, CONST.HEAD_TYPE_VAL);
			uriBuilder.addParameter("-", TimeUtil.getTimeLenth(13));
			final HttpGet request = new HttpGet(uriBuilder.build());
			request.setConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.NETSCAPE).build());
			return request;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserInfo execute(final HttpGet request) {
		httpClient = HttpClients.custom()
				.setConnectionManager(PoolingHttpClientConnection.getInstalce())
				.addInterceptorLast(requestInterceptor)
				.setDefaultCookieStore(cookieStoreUT.readCookieStoreForDisk(CONST.BASE_COOKIES_VALUES))
				.build();
		try {
			return httpClient.execute(request, responseHandler, this.httpClientContext);
		} catch (IOException e) {
			executeException(e,request);
		}
		return null;
	}

}
