package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.DifferPressParser;
import com.dounine.clouddisk360.parser.deserializer.differpre.DifferPress;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

public class DifferPressParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(DifferPressParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final DifferPressParser differPressParser = new DifferPressParser(loginUserToken);
		final DifferPress differPress = differPressParser.parse();
		try {
			LOGGER.info(differPress.getRedirectUrl().build().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
