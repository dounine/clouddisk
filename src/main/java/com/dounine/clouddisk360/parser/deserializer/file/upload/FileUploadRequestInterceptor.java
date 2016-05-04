package com.dounine.clouddisk360.parser.deserializer.file.upload;

import com.dounine.clouddisk360.parser.FileUploadParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileUploadRequestInterceptor extends BaseRequestInterceptor<FileUploadConst,FileUploadParser> implements HttpRequestInterceptor{

	public FileUploadRequestInterceptor(final FileUploadParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
