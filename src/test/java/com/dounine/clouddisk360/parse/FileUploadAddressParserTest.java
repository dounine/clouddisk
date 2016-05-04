package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileUploadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.file.download.upaddress.FileUploadAddress;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadAddressParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadAddressParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;
		
		final FileUploadAddressParser fileUploadAddressParser = new FileUploadAddressParser(loginUserToken);
		final FileUploadAddress fileUploadAddress = fileUploadAddressParser.parse();
		
		LOGGER.info(fileUploadAddress.toString());
	}
}
