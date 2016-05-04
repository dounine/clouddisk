package com.dounine.clouddisk360.parser.deserializer.file.history;

import com.dounine.clouddisk360.parser.FileHistoryParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileHistoryRequestInterceptor extends BaseRequestInterceptor<FileHistoryConst,FileHistoryParser> implements HttpRequestInterceptor{

	public FileHistoryRequestInterceptor(final FileHistoryParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
