package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileAddFileParser;
import com.dounine.clouddisk360.parser.FileUploadParser;
import com.dounine.clouddisk360.parser.deserializer.file.upload.FileUpload;
import com.dounine.clouddisk360.parser.deserializer.file.upload.FileUploadParameter;
import com.dounine.clouddisk360.parser.deserializer.file.upload.addfile.FileAddFile;
import com.dounine.clouddisk360.parser.deserializer.file.upload.addfile.FileAddFileParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileAddParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileAddParserTest.class);

	public void testDependParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileUploadParser fileUploadParser = new FileUploadParser(loginUserToken);
		FileUploadParameter fileUploadParameter = new FileUploadParameter();
		fileUploadParameter.setPath("/");
		fileUploadParameter.setUploadFile(new File("/Users/huanghuanlai/java/lake/lake副本.txt"));
		FileUpload fileUpload = fileUploadParser.parse(fileUploadParameter);

		FileAddFileParser fileAddParser = new FileAddFileParser();
		fileAddParser.dataSmooth(fileUploadParser);// 数据平滑
		FileAddFileParameter fileAddParameter = new FileAddFileParameter();
		fileAddParameter.setTk(fileUpload.getData().getTk());
		FileAddFile fileAdd = fileAddParser.parse(fileAddParameter);

		LOGGER.info(fileAdd.toString());
	}
}
