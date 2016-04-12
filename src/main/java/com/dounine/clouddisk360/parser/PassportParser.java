package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.captthread.CaptThread;
import com.dounine.clouddisk360.parser.deserializer.captcha.Captcha;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.passport.*;
import com.dounine.clouddisk360.parser.deserializer.user.check.UserCheckLogin;
import org.apache.http.client.methods.HttpGet;

@Parse("验证码显示")
public class PassportParser extends
		BaseParser<HttpGet, Passport, PassportConst, PassportParameter, PassportRequestInterceptor, PassportResponseHandle, PassportParser> {

	public PassportParser() {
		super();
	}

	public PassportParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpGet initRequest(PassportParameter passportParameter) {
		return new HttpGet(parameter.getUri());
	}

	@Override
	public Passport parse(PassportParameter parameter) {
		if(parameter.isManual()){//手动检查
			UserCheckLoginParser userCheckLoginParser = new UserCheckLoginParser(loginUserToken);
			UserCheckLogin userCheckLogin = userCheckLoginParser.parse();
			if(userCheckLogin.getErrno()!=0){//没有登录可获取验证码
				CaptchaParser captchaParser = new CaptchaParser();
				captchaParser.dataSmooth(userCheckLoginParser);//数据平滑
				Captcha captcha = captchaParser.parse();
				parameter.setUri(captcha.getCaptchaUrl());
				if(captcha.getCaptchaFlag()){//需要验码
					Passport passport = super.parse(parameter);
					CaptThread.push_passport(loginUserToken.getAccount(),this);//手动更新验证码
					return passport;
				}
			}
		}
		return super.parse(parameter);
	}
}
