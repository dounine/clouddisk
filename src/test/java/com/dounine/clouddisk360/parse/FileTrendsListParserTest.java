package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileTrendsListParser;
import com.dounine.clouddisk360.parser.deserializer.file.trends.FileTrendsList;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTrendsListParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileTrendsListParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final FileTrendsListParser fileTrendsListParser = new FileTrendsListParser(loginUserToken);
		final FileTrendsList fileTrendsList = fileTrendsListParser.parse();

		LOGGER.info(fileTrendsList.toString());
	}
}
