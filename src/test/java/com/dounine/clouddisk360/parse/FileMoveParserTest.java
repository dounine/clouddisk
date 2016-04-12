package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileMoveParser;
import com.dounine.clouddisk360.parser.deserializer.file.move.FileMove;
import com.dounine.clouddisk360.parser.deserializer.file.move.FileMoveParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMoveParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileMoveParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileMoveParser fileMoveParser = new FileMoveParser(loginUserToken);
		FileMoveParameter fileMoveParameter = new FileMoveParameter();
		fileMoveParameter.getMoveFiles().add("/a/");
		fileMoveParameter.setNewPath("/黄/");
		FileMove fileMove = fileMoveParser.parse(fileMoveParameter);

		LOGGER.info(fileMove.toString());
	}
}
