package com.dounine.clouddisk360.parser;

import org.apache.http.client.methods.HttpGet;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFile;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFileConst;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFileParameter;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFileRequestInterceptor;
import com.dounine.clouddisk360.parser.deserializer.file.download.FileDownloadFileResponseHandle;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;

@Parse("判断文件是否存在")
public class FileDownloadFileParser extends
		BaseParser<HttpGet, FileDownloadFile, FileDownloadFileConst, FileDownloadFileParameter, FileDownloadFileRequestInterceptor, FileDownloadFileResponseHandle, FileDownloadFileParser> {

	public FileDownloadFileParser() {
		super();
	}

	public FileDownloadFileParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public HttpGet initRequest(final FileDownloadFileParameter parameter) {
		return new HttpGet(parameter.getDownloadPath());
	}

}
