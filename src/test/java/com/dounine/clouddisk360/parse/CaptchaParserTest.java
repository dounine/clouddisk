package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.CaptchaParser;
import com.dounine.clouddisk360.parser.deserializer.captcha.Captcha;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptchaParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		CaptchaParser captchaParser = new CaptchaParser(loginUserToken);
		Captcha captcha = captchaParser.parse();

		LOGGER.info(captcha.toString());
	}
}
