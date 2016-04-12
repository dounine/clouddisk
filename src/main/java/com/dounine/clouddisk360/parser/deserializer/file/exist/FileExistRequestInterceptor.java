package com.dounine.clouddisk360.parser.deserializer.file.exist;

import com.dounine.clouddisk360.parser.FileExistParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileExistRequestInterceptor extends BaseRequestInterceptor<FileExistConst,FileExistParser> implements HttpRequestInterceptor{

	public FileExistRequestInterceptor(FileExistParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
