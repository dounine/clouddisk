package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.UserSizeParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.user.size.UserSize;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSizeParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSizeParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = new LoginUserToken("102535481@qq.com","");

		UserSizeParser userSizeParser = new UserSizeParser(loginUserToken);
		UserSize userSize = userSizeParser.parse();

		userSizeParser.setBianaryFilename("user/userSize.txt");

		LOGGER.info(userSize.toString());
	}
}
