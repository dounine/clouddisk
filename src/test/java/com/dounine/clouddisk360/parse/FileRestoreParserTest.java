package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileRestoreParser;
import com.dounine.clouddisk360.parser.deserializer.file.history.restore.FileRestore;
import com.dounine.clouddisk360.parser.deserializer.file.history.restore.FileRestoreParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRestoreParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileRestoreParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final FileRestoreParser fileRestoreParser = new FileRestoreParser(loginUserToken);
		final FileRestoreParameter fileRestoreParameter = new FileRestoreParameter();
		fileRestoreParameter.setNid("14502383266144986");
		fileRestoreParameter.setId("56e1395daf263c86518be190");
		final FileRestore fileRestore = fileRestoreParser.parse(fileRestoreParameter);

		LOGGER.info(fileRestore.toString());
	}
}
