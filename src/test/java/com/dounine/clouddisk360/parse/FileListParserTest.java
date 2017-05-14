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
		final LoginUserToken loginUserToken = new LoginUserToken("102535481@qq.com","",false);
		final FileListParser fileListParser = new FileListParser(loginUserToken);
		final FileListParameter fileListParameter = new FileListParameter();
		fileListParameter.setOrder(Order.DESC);
		fileListParameter.setPage(0);
		fileListParameter.setPage_size(300);
		fileListParameter.setPath(FileListParameter.ROOT_PATH);
		final FileList fileList = fileListParser.parse(fileListParameter);
		LOGGER.info(fileList.toString());
	}
}
