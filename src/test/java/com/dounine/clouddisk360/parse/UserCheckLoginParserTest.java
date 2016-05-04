package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.UserCheckLoginParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.check.UserCheckLogin;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCheckLoginParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCheckLoginParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final UserCheckLoginParser userInfoParser = new UserCheckLoginParser(loginUserToken);
		final UserCheckLogin userInfo = userInfoParser.parse();

		LOGGER.info(userInfo.getErrno()+"");
	}
}
