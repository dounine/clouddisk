package com.dounine.clouddisk360.parser.deserializer.file.download;

import com.dounine.clouddisk360.parser.FileDownloadFileParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileDownloadFileRequestInterceptor extends BaseRequestInterceptor<FileDownloadFileConst,FileDownloadFileParser>
		implements HttpRequestInterceptor {

	public FileDownloadFileRequestInterceptor(final FileDownloadFileParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
