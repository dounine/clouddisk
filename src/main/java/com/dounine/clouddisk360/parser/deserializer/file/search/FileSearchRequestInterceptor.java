package com.dounine.clouddisk360.parser.deserializer.file.search;

import com.dounine.clouddisk360.parser.FileSearchParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileSearchRequestInterceptor extends BaseRequestInterceptor<FileSearchConst,FileSearchParser> implements HttpRequestInterceptor{

	public FileSearchRequestInterceptor(final FileSearchParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
