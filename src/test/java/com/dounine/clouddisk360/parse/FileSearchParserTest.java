package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileSearchParser;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearch;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearchParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSearchParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSearchParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileSearchParser fileSearchParser = new FileSearchParser(loginUserToken);
		FileSearchParameter fileSearchParameter = new FileSearchParameter();
		fileSearchParameter.setKey("lake");
		fileSearchParameter.setPage(0);
		fileSearchParameter.setPage_size(50);
		FileSearch fileSearch = fileSearchParser.parse(fileSearchParameter);

		LOGGER.info(fileSearch.toString());
	}
}
