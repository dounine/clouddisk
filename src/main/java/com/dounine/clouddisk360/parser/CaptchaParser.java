package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.DependResult;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.captcha.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

@Parse("验证码")
@DependResult(result = Captcha.class, customInit = false)
public class CaptchaParser extends
		BaseParser<HttpGet, Captcha, CaptchaConst, CaptchaParameter, CaptchaRequestInterceptor, CaptchaResponseHandle, CaptchaParser> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaParser.class);
	public CaptchaParser() {
		super();
	}

	public CaptchaParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	public HttpGet initRequest(CaptchaParameter parameter) {
		HttpGet request = null;
		try {
			final URIBuilder uri = new URIBuilder(CONST.URI_PATH);
			uri.setParameter(CONST.SRC_KEY, CONST.SRC_VAL);
			uri.setParameter(CONST.FROM_KEY, CONST.FROM_VAL);
			uri.setParameter(CONST.CHARSET_KEY, CONST.CHARSET_VAL);
			uri.setParameter(CONST.REQUESTSCEMA_KEY, CONST.REQUESTSCEMA_VAL);
			uri.setParameter(CONST.O_KEY, CONST.O_VAL);
			uri.setParameter(CONST.M_KEY, CONST.M_VAL);
			uri.setParameter(CONST.ACCOUNT_NAME, loginUserToken.getAccount());
			uri.setParameter(CONST.CAPTCHAAPP_KEY, CONST.CAPTCHAAPP_VAL);
			request = new HttpGet(uri.build());
		} catch (URISyntaxException e) {
			LOGGER.error("Error",e);
		}
		return request;
	}

}
