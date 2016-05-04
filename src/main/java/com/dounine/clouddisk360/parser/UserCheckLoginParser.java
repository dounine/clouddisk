package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginConst;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.check.*;
import com.dounine.clouddisk360.pool.PoolingHttpClientConnection;
import com.dounine.clouddisk360.util.TimeUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Parse("用户信息")
public class UserCheckLoginParser extends
		BaseParser<HttpPost, UserCheckLogin, UserCheckLoginConst, UserCheckLoginParameter, UserCheckLoginRequestInterceptor, UserCheckLoginResponseHandle,UserCheckLoginParser> {

	public UserCheckLoginParser(){
		super();
	}
	public UserCheckLoginParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpPost initRequest(final UserCheckLoginParameter userCheckLoginParameter) {
		setBinaryFilename(LoginConst.USER_INFO_PATH_NAME);
		Login login = readObjForDisk(Login.class);
		if(null==login){
			login = new Login();
		}
		final HttpPost request = new HttpPost(CONST.URI_PATH);
		final List<NameValuePair> data = new ArrayList<>();
		data.add(new BasicNameValuePair(CONST.QID_NAME, login.getQid()));
		data.add(new BasicNameValuePair(CONST.METHOD_KEY, CONST.METHOD_VAL));
		data.add(new BasicNameValuePair(CONST.AJAX_KEY, CONST.AJAX_VAL));
		data.add(new BasicNameValuePair("t", TimeUtil.getTimeLenth(13)));
		request.setConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.NETSCAPE).build());
		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));
		return request;
	}

	@Override
	public UserCheckLogin execute(final HttpPost request) {
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
