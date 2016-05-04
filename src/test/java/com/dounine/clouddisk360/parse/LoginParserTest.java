package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.LoginParser;
import com.dounine.clouddisk360.parser.PassportParser;
import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.captcha.Captcha;
import com.dounine.clouddisk360.parser.deserializer.login.Login;
import com.dounine.clouddisk360.parser.deserializer.login.LoginParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.passport.PassportParameter;
import com.dounine.clouddisk360.parser.deserializer.user.token.UserToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LoginParserTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginParserTest.class);

	@Test
	public void testFirst() {
		login(TestUser.LOGIN_USER_TOKEN);
	}

	public void login(LoginUserToken loginUserToken) {

		final LoginParser loginParser = new LoginParser(loginUserToken);
		final Captcha captcha = loginParser.getDependResult(Captcha.class);
		final LoginParameter loginParameter = new LoginParameter();
		if (captcha.getCaptchaFlag()) {
			final PassportParser passportParser = new PassportParser();
			passportParser.dataSmooth(loginParser);
			final PassportParameter passportParameter = new PassportParameter();
			passportParameter.setUri(captcha.getCaptchaUrl());
			passportParser.parse(passportParameter);

			System.out.print("请输入验证码:");
			final Scanner scanner = new Scanner(System.in);
			String captchaValue = scanner.nextLine().trim();
			scanner.close();

			loginParameter.setCaptchaValue(captchaValue);
			loginParser.dataSmooth(passportParser);// 数据平滑
		}
		final UserTokenParser userTokenParser = new UserTokenParser();
		userTokenParser.dataSmooth(loginParser);// 数据平滑
		final UserToken userToken = userTokenParser.parse();
		loginParameter.setToken(userToken.getToken());

		final Login login = loginParser.parse(loginParameter);

		LOGGER.info(login.toString());
	}

}
