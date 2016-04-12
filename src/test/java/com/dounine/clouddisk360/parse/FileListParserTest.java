package com.dounine.clouddisk360.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dounine.clouddisk360.enums.Order;
import com.dounine.clouddisk360.parser.FileListParser;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileList;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;

import junit.framework.TestCase;

public class FileListParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileListParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileListParser fileListParser = new FileListParser(loginUserToken);
		FileListParameter fileListParameter = new FileListParameter();
		fileListParameter.setOrder(Order.DESC);
		fileListParameter.setPage(0);
		fileListParameter.setPage_size(300);
		fileListParameter.setPath(fileListParameter.ROOT_PATH);
		FileList fileList = fileListParser.parse(fileListParameter);
		LOGGER.info(fileList.toString());
		LOGGER.info(fileListParser.readAuthTokenValue());

	}
}
