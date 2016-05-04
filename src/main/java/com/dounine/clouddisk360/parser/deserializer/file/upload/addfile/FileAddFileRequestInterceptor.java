package com.dounine.clouddisk360.parser.deserializer.file.upload.addfile;

import com.dounine.clouddisk360.parser.FileAddFileParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileAddFileRequestInterceptor extends BaseRequestInterceptor<FileAddFileConst,FileAddFileParser> implements HttpRequestInterceptor{

	public FileAddFileRequestInterceptor(final FileAddFileParser parser) {
		super(parser);
	}

	@Override
	public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
