package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.UserInfoParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.info.UserInfo;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfoParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final UserInfoParser userInfoParser = new UserInfoParser(loginUserToken);
		final UserInfo userInfo = userInfoParser.parse();

		LOGGER.info(userInfo.getErrno()+"");
	}
}
