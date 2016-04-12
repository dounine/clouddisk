package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.UserSigninParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.signin.UserSignin;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSigninParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSigninParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		UserSigninParser userSigninParser = new UserSigninParser(loginUserToken);
		UserSignin userSignin = userSigninParser.parse();

		LOGGER.info(userSignin.toString());
	}
}
