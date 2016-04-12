package com.dounine.clouddisk360.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dounine.clouddisk360.parser.FileInfoParser;
import com.dounine.clouddisk360.parser.deserializer.file.info.FileInfo;
import com.dounine.clouddisk360.parser.deserializer.file.info.FileInfoParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;

import junit.framework.TestCase;

public class FileInfoParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoParserTest.class);

	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileInfoParser fileInfoParser = new FileInfoParser(loginUserToken);
		FileInfoParameter fileInfoParameter = new FileInfoParameter();
		fileInfoParameter.setFilePath("/黄/工作日报最新模板.xlsx");
		FileInfo fileInfo = fileInfoParser.parse(fileInfoParameter);

		LOGGER.info(fileInfo.toString());

	}
}
