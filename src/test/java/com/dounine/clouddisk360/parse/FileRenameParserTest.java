package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileRenameParser;
import com.dounine.clouddisk360.parser.deserializer.file.rename.FileRename;
import com.dounine.clouddisk360.parser.deserializer.file.rename.FileRenameParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRenameParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileRenameParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final FileRenameParser fileRenameParser = new FileRenameParser(loginUserToken);
		final FileRenameParameter fileRenameParameter = new FileRenameParameter();
		fileRenameParameter.setPath("/lake/lake3/");
		fileRenameParameter.setNid("14575078756171719");
		fileRenameParameter.setNewpath("lake2/");
		final FileRename fileRename = fileRenameParser.parse(fileRenameParameter);

		LOGGER.info(fileRename.toString());
	}
}
