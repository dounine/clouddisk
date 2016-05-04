package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileHistoryParser;
import com.dounine.clouddisk360.parser.deserializer.file.history.FileHistory;
import com.dounine.clouddisk360.parser.deserializer.file.history.FileHistoryParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHistoryParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileHistoryParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final FileHistoryParser fileHistoryParser = new FileHistoryParser(loginUserToken);
		final FileHistoryParameter fileHistoryParameter = new FileHistoryParameter();
		fileHistoryParameter.setHis_nid("14502383266144986");
		fileHistoryParameter.setNid("14502383266144986");
		fileHistoryParameter.setStart("0");
		fileHistoryParameter.setNum("300");
		fileHistoryParameter.setSource(StringUtils.EMPTY);
		final FileHistory fileHistory = fileHistoryParser.parse(fileHistoryParameter);

		LOGGER.info(fileHistory.toString());
	}
}
