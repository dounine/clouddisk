package com.dounine.clouddisk360.parser.deserializer.file.list;

import com.dounine.clouddisk360.parser.FileListParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileListRequestInterceptor extends BaseRequestInterceptor<FileListConst,FileListParser>
		implements HttpRequestInterceptor {

	public FileListRequestInterceptor(final FileListParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context, true);
	}

}
