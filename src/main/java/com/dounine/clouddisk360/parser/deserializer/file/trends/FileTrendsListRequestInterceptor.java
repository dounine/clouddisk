package com.dounine.clouddisk360.parser.deserializer.file.trends;

import com.dounine.clouddisk360.parser.FileTrendsListParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileTrendsListRequestInterceptor extends BaseRequestInterceptor<FileTrendsListConst,FileTrendsListParser> implements HttpRequestInterceptor{

	public FileTrendsListRequestInterceptor(FileTrendsListParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
