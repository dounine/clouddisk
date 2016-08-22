package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.login.*;
import com.dounine.clouddisk360.store.BasePathCommon;
import com.dounine.clouddisk360.store.CookieStoreUT;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Parse("用户登录")
@Dependency(depends = {CaptchaParser.class})//不可删除
public class LoginParser extends
		BaseParser<HttpPost, Login, LoginConst, LoginParameter, LoginRequestInterceptor, LoginResponseHandle, LoginParser> {

	public LoginParser() {
		super();
		deleteCookieStoreDir();
	}

	public LoginParser(final LoginUserToken loginUser) {
		super(loginUser);
		deleteCookieStoreDir();
	}

	public void deleteCookieStoreDir(){
		final File file = new File(BasePathCommon.BASE_PATH + loginUserToken.getAccount()+ CookieStoreUT.COOKIE_STORE_PATH);
		if(file.exists()){
			file.delete();
		}
	}

	@Override
	public HttpPost initRequest(final LoginParameter parameter) {
		final HttpPost request = new HttpPost(CONST.URI_PATH);
		final List<NameValuePair> data = new ArrayList<>();
		data.add(new BasicNameValuePair(CONST.SRC_KEY, CONST.SRC_VAL));
		data.add(new BasicNameValuePair(CONST.FROM_KEY, CONST.FROM_VAL));
		data.add(new BasicNameValuePair(CONST.CHARSET_KEY, CONST.SRC_VAL));
		data.add(new BasicNameValuePair(CONST.REQUESTSCEMA_KEY, CONST.REQUESTSCEMA_VAL));
		data.add(new BasicNameValuePair(CONST.O_KEY, CONST.O_VAL));
		data.add(new BasicNameValuePair(CONST.M_KEY, CONST.M_VAL));
		data.add(new BasicNameValuePair(CONST.LM_KEY, CONST.LM_VAL));
		data.add(new BasicNameValuePair(CONST.CAPTFLAG_KEY, CONST.CAPTFLAG_VAL));
		data.add(new BasicNameValuePair(CONST.RTYPE_KEY, CONST.RTYPE_VAL));
		data.add(new BasicNameValuePair(CONST.VALIDATELM_KEY, CONST.VALIDATELM_VAL));
		data.add(new BasicNameValuePair(CONST.ISKEEPALIVE_KEY, CONST.ISKEEPALIVE_VAL));
		data.add(new BasicNameValuePair(CONST.CAPTCHAAPP_KEY, CONST.CAPTCHAAPP_VAL));
		data.add(new BasicNameValuePair(CONST.USERNAME_NAME, loginUserToken.getAccount()));
		data.add(new BasicNameValuePair(CONST.TYPE_KEY, LoginConst.TYPE_VAL));
		data.add(new BasicNameValuePair(CONST.ACCOUNT_NAME, loginUserToken.getAccount()));
        data.add(new BasicNameValuePair(CONST.PASSWORD_NAME, loginUserToken.getPassword()));
		if (StringUtils.isNotBlank(parameter.getCaptchaValue())) {
			data.add(new BasicNameValuePair(CONST.CAPTCHA_KEY, parameter.getCaptchaValue()));
		} else {
			data.add(new BasicNameValuePair(CONST.CAPTCHA_KEY, StringUtils.EMPTY));
		}
		if (StringUtils.isNotBlank(parameter.getToken())) {
			data.add(new BasicNameValuePair(CONST.TOKEN_NAME, parameter.getToken()));
		} else {
			throw new CloudDiskException("登录所需token令牌不能为空.");
		}
		data.add(new BasicNameValuePair(LoginConst.PROXY_KEY, LoginConst.PROXY_VAL));

		request.setEntity(new UrlEncodedFormEntity(data, Consts.UTF_8));

		return request;
	}
}
