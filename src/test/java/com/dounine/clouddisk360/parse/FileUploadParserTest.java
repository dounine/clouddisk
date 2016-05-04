package com.dounine.clouddisk360.parse;

import com.dounine.clouddisk360.parser.FileExistParser;
import com.dounine.clouddisk360.parser.FileUploadParser;
import com.dounine.clouddisk360.parser.deserializer.file.exist.FileExist;
import com.dounine.clouddisk360.parser.deserializer.file.exist.FileExistParameter;
import com.dounine.clouddisk360.parser.deserializer.file.upload.FileUpload;
import com.dounine.clouddisk360.parser.deserializer.file.upload.FileUploadParameter;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUploadParserTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadParserTest.class);

	/**
	 * 使用说明(以下两种方式请自己进行取舍):
	 * 方法1:上传文件可以用自己的服务器作为中转站(用户先上传文件到自己服务器再上传到云盘上,这种方法安全,但容易掉线失败,并且占用自己服务器的空间)
	 * 方法2:把用户登录的token作用授权的唯一方式,让用户进行文件的上传操作,此方法安全性极低,但性能极高
	 *
	 * 表单的编写方式如下,注(xxxxxxx)内容请根据实际情况进行替换填写
	 * <form action="http://xxxxxxxxxxxxx/webupload?devtype=web" enctype="multipart/form-data" method="post">
			<input type="text" name="qid" value="1256978945" />
			<input type="text" name="ofmt" value="json" />
			<input type="text" name="method" value="Upload.web" />
			<input type="text" name="token" value="2004996475.38.ff97a9d3xxxxxxxxxx" />
			<input type="text" name="v" value="1.0.1" />
			<input type="text" name="tk" value="xxxxxxxx" />
			<input type="text" name="Upload" value="Submit Query" />
			<input type="text" name="devtype" value="web" />
			<input type="text" name="pid" value="ajax" />
			<input type="text" name="path" value="/" />
			<input type="file"  name="file" />
			<input type="submit" value="上传" />
		</form>
	 */

	public void testParse() {
		final LoginUserToken loginUserToken = TestUser.LOGIN_USER_TOKEN;

		final File file = new File("/Users/huanghuanlai/java/lake/lake副本.txt");

		final FileExistParser fileExistParser = new FileExistParser(loginUserToken);
		final FileExistParameter fileExistParameter = new FileExistParameter();
		fileExistParameter.setDir("/");
		final List<String> fnames = new ArrayList<>();
		fnames.add(file.getName());
		fileExistParameter.setFnames(fnames);
		final FileExist fileExist = fileExistParser.parse(fileExistParameter);

		final FileUploadParser fileUploadParser = new FileUploadParser(loginUserToken);
		final FileUploadParameter fileUploadParameter = new FileUploadParameter();
		fileUploadParameter.setPath("/");
		fileUploadParameter.setUploadFile(file);
		final FileUpload fileUpload = fileUploadParser.parse(fileUploadParameter);

		LOGGER.info(fileUpload.toString());
	}
}
