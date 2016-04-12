package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.UserTokenParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.token.UserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTokenParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = new LoginUserToken("102535481@qq.com","");

		UserTokenParser userTokenParse = new UserTokenParser(loginUserToken);
		UserToken userToken = userTokenParse.parse();

		LOGGER.info(userToken.toString());
	}
}
