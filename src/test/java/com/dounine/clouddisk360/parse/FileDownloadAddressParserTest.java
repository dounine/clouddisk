package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileDownloadAddressParser;
import com.dounine.clouddisk360.parser.deserializer.file.download.dladdress.FileDownloadAddress;
import com.dounine.clouddisk360.parser.deserializer.file.download.dladdress.FileDownloadAddressParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloadAddressParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadAddressParserTest.class);

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final FileDownloadAddressParser fileDownloadAddressParser = new FileDownloadAddressParser(loginUserToken);
		final FileDownloadAddressParameter fileDownloadAddressParameter = new FileDownloadAddressParameter();
		fileDownloadAddressParameter.setFname("/lake.txt");
		fileDownloadAddressParameter.setNid("14502383266144986");
		final FileDownloadAddress fileDownloadAddress = fileDownloadAddressParser.parse(fileDownloadAddressParameter);

		LOGGER.info(fileDownloadAddress.toString());
	}
}
