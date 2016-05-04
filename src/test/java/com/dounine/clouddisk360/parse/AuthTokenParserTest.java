package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.AuthTokenParser;
import com.dounine.clouddisk360.parser.deserializer.authtoken.AuthToken;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthTokenParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenParserTest.class);

	public void testDependParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final AuthTokenParser authTokenParse = new AuthTokenParser(loginUserToken);
		final AuthToken authToken = authTokenParse.parse();
		LOGGER.info(authToken.getToken());
	}
}
