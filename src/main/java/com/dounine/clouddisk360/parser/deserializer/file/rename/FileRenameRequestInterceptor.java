package com.dounine.clouddisk360.parser.deserializer.file.rename;

import com.dounine.clouddisk360.parser.FileRenameParser;
import com.dounine.clouddisk360.parser.deserializer.BaseRequestInterceptor;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class FileRenameRequestInterceptor extends BaseRequestInterceptor<FileRenameConst,FileRenameParser> implements HttpRequestInterceptor{

	public FileRenameRequestInterceptor(FileRenameParser parser) {
		super(parser);
	}

	@Override
	public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		super.process(request, context,true);
	}

}
