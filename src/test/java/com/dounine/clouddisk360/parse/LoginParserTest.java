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
		LoginParser loginParser = new LoginParser(loginUserToken);
		Captcha captcha = loginParser.getDependResult(Captcha.class);

		LoginParameter loginParameter = new LoginParameter();
		if (captcha.getCaptchaFlag()) {
			PassportParser passportParser = new PassportParser();
			passportParser.dataSmooth(loginParser);
			PassportParameter passportParameter = new PassportParameter();
			passportParameter.setUri(captcha.getCaptchaUrl());
			passportParser.parse(passportParameter);

			System.out.print("请输入验证码:");
			Scanner scanner = new Scanner(System.in);
			String captchaValue = scanner.nextLine().trim();
			scanner.close();

			loginParameter.setCaptchaValue(captchaValue);
			loginParser.dataSmooth(passportParser);// 数据平滑
		}
		UserTokenParser userTokenParser = new UserTokenParser();
		userTokenParser.dataSmooth(loginParser);// 数据平滑
		UserToken userToken = userTokenParser.parse();
		loginParameter.setToken(userToken.getToken());

		Login login = loginParser.parse(loginParameter);

		LOGGER.info(login.toString());
	}

}
