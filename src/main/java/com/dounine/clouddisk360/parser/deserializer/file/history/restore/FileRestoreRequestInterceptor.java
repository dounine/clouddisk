package com.dounine.clouddisk360.parser.deserializer.file.history.restore;

import com.dounine.clouddisk360.parser.FileRestoreParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileRestoreRequestInterceptor extends BaseRequestInterceptor<FileRestoreConst,FileRestoreParser> implements HttpRequestInterceptor{

	public FileRestoreRequestInterceptor(FileRestoreParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
