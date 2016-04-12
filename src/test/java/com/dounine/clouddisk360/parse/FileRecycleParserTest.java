package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileRecycleParser;
import com.dounine.clouddisk360.parser.deserializer.file.recycle.FileRecycle;
import com.dounine.clouddisk360.parser.deserializer.file.recycle.FileRecycleParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRecycleParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileRecycleParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileRecycleParser fileRecycleParser = new FileRecycleParser(loginUserToken);
		FileRecycleParameter fileRecycleParameter = new FileRecycleParameter();
		fileRecycleParameter.getPath().add("/lake/现有功能.xlsx");
		fileRecycleParameter.getPath().add("/lake/lake2/");
		FileRecycle fileRecycle = fileRecycleParser.parse(fileRecycleParameter);

		LOGGER.info(fileRecycle.toString());
	}
}
