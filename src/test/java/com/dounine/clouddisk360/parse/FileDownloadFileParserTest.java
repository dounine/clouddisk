package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileDownloadAddressParser;
import com.dounine.clouddisk360.parser.FileDownloadFileParser;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFile;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFileParameter;
import com.dounine.clouddisk360.parser.deserializer.file.download.dladdress.FileDownloadAddress;
import com.dounine.clouddisk360.parser.deserializer.file.download.dladdress.FileDownloadAddressParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDownloadFileParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadFileParserTest.class);

	/**
	 * 使用说明(以下两种方式请自己进行取舍):
	 * 方法1:下载文件可以用自己的服务器作为中转站(服务器可以先下载文件到自己服务器上然后再给用户访问,这种方法安全,但占用自己服务器的空间)
	 * 方法2:把用户登录的token作用授权的唯一方式,让用户进行文件的下载操作,此方法安全性极低,但性能极高,不占服务器带宽
	 * 
	 * 表单的编写方式如下,注(xxxxxxx)内容请根据实际情况进行替换填写
	 * <form action="http://xxxxxx/intf.php?method=Download.downloadFile&xxxxxxxxx" method="post">
			<input type="text" name="token" value="2004996475.38.ff97a9d3.xxxxxxxxxxx" />
			<input type="submit" value="下载" />
		</form>
	 */
	public void testParse() {
		LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		FileDownloadAddressParser fileDownloadAddressParser = new FileDownloadAddressParser(loginUserToken);
		FileDownloadAddressParameter fileDownloadAddressParameter = new FileDownloadAddressParameter();
		fileDownloadAddressParameter.setFname("/黄/工作日报最新模板.xlsx");
		fileDownloadAddressParameter.setNid("14576185026173704");
		FileDownloadAddress fileDownloadAddress = fileDownloadAddressParser.parse(fileDownloadAddressParameter);

		FileDownloadFileParser fileDownloadFileParser = new FileDownloadFileParser();
		fileDownloadFileParser.dataSmooth(fileDownloadAddressParser);
		FileDownloadFileParameter fileDownloadFileParameter = new FileDownloadFileParameter();
		fileDownloadFileParameter.setDownloadPath(fileDownloadAddress.getData().getDownload_url());
		fileDownloadFileParameter.setFileName(fileDownloadAddressParameter.getFname());
		FileDownloadFile fileDownloadFile = fileDownloadFileParser.parse(fileDownloadFileParameter);

		LOGGER.info(fileDownloadFile.toString());
	}
}
