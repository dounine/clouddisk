package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileExistParser;
import com.dounine.clouddisk360.parser.deserializer.file.exist.FileExist;
import com.dounine.clouddisk360.parser.deserializer.file.exist.FileExistParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FileExistParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileExistParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileExistParser fileExistParser = new FileExistParser(loginUserToken);
		FileExistParameter fileExistParameter = new FileExistParameter();
		fileExistParameter.setDir("/");
		List<String> fnames = new ArrayList<>();
		fnames.add("加班记录.numbers");
		fileExistParameter.setFnames(fnames);
		FileExist fileExist = fileExistParser.parse(fileExistParameter);

		LOGGER.info(fileExist.toString());
	}
}
